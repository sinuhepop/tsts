package tk.spop.tsts.xml;

import java.io.ByteArrayOutputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.CompositeStringExpression;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.writer.OutputStreamCodeWriter;

import lombok.Setter;
import lombok.SneakyThrows;
import lombok.val;
import tk.spop.tsts.ClassGenerator;
import tk.spop.tsts.CompilationContext;
import tk.spop.tsts.Constants;
import tk.spop.tsts.directive.DirectiveRegistry;
import tk.spop.tsts.model.ImportDefinition;

@Setter
public class XmlClassGenerator implements ClassGenerator {

	private ImportDefinition imports;
	private DirectiveRegistry directives;

	private final SpelExpressionParser parser = new SpelExpressionParser();

	@Override
	@SneakyThrows
	public String generate(Path path, String content) {

		val nodes = XmlUtils.read(content);

		val cm = new JCodeModel();
		val pkg = cm._package(path.getParent().toString().replace('/', '.').replace('\\', '.'));
		val clss = pkg._class(path.getFileName().toString());

		val ctx = new CompilationContext();
		ctx.setGenerator(this);
		ctx.setCurrentClass(clss);
		ctx.setImports(imports.clone());

		processList(ctx, nodes);

		return addImportDefinitions(getSource(clss));
	}

	@SneakyThrows
	protected String getSource(JDefinedClass clss) {
		val os = new ByteArrayOutputStream();
		val writer = new OutputStreamCodeWriter(os, Constants.ENCODING);
		clss.getPackage().owner().build(writer);
		return os.toString(Constants.ENCODING.displayName());
	}

	@SneakyThrows
	protected String addImportDefinitions(String source) {
		return imports == null ? source : source.replaceFirst(";", ";\n\n" + imports.toString());
	}

	public void processList(CompilationContext ctx, NodeList list) {
		for (int i = 0; i < list.getLength(); i++) {
			processNode(ctx, list.item(i));
		}
	}

	public void processNode(CompilationContext ctx, Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			val key = parseDirectiveKey(node.getNodeName());
			if (key != null) {
				processDirective(ctx, (Element) node);
			} else {
				processElement(ctx, (Element) node);
			}
		} else if (node.getNodeType() == Node.TEXT_NODE) {
			processText(ctx, (Text) node);
		}
	}

	public void processDirective(CompilationContext ctx, Element node) {
		val key = parseDirectiveKey(node.getNodeName());
		val directive = directives.get(key);
		if (directive != null) {
			directive.run(ctx, node);
		}
	}

	public void processElement(CompilationContext ctx, Element node) {
		ctx.append("<" + node.getTagName());
		for (val attr : getAttributes(node).entrySet()) {
			ctx.append(" " + attr.getKey() + "='" + attr.getValue().replace("'", "\\'") + "'");
		}
		ctx.append(">");
		processList(ctx, node.getChildNodes());
		ctx.append("</" + node.getTagName() + ">");
	}

	public void processText(CompilationContext ctx, Text node) {
		val exp = parser.parseExpression(node.getTextContent(), ParserContext.TEMPLATE_EXPRESSION);
		processExpression(ctx, exp);
	}

	protected void processExpression(CompilationContext ctx, Expression exp) {
		if (exp instanceof CompositeStringExpression) {
			for (val sub : ((CompositeStringExpression) exp).getExpressions()) {
				processExpression(ctx, sub);
			}
		} else if (exp instanceof LiteralExpression) {
			val literal = ((LiteralExpression) exp).getExpressionString();
			ctx.append(literal);
		} else {
			ctx.flush();
			val javaExp = ((SpelExpression) exp).getExpressionString();
			ctx.getCurrentBlock().directStatement("ctx.writeText(args." + javaExp + ");");
		}
	}

	public String parseDirectiveKey(String nodeName) {
		return nodeName.startsWith(":") ? nodeName.substring(1) : null;
	}

	public Map<String, String> getAttributes(Element node) {
		val map = node.getAttributes();
		return IntStream.range(0, map.getLength()) //
				.mapToObj(i -> map.item(i)) //
				.collect(Collectors.toMap(Node::getNodeName, Node::getNodeValue));
	}

}
