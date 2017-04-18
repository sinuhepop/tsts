package tk.spop.tsts;

import java.io.ByteArrayOutputStream;
import java.nio.file.Path;
import java.util.List;

import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.CompositeStringExpression;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.writer.OutputStreamCodeWriter;

import lombok.Setter;
import lombok.SneakyThrows;
import lombok.val;
import tk.spop.tsts.directive.DirectiveRegistry;
import tk.spop.tsts.model.ImportDefinition;
import tk.spop.tsts.model.ast.AstElement;
import tk.spop.tsts.model.ast.AstNode;
import tk.spop.tsts.model.ast.AstText;
import tk.spop.tsts.xml.XmlUtils;

@Setter
public abstract class AbstractClassGenerator implements ClassGenerator {

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

	public void processList(CompilationContext ctx, List<AstNode> list) {
		list.forEach(node -> processNode(ctx, node));
	}

	public void processNode(CompilationContext ctx, AstNode node) {
		if (node instanceof AstElement) {
			if (((AstElement) node).isDirective()) {
				processDirective(ctx, (AstElement) node);
			} else {
				processElement(ctx, (AstElement) node);
			}
		} else {
			processText(ctx, (AstText) node);
		}
	}

	public void processDirective(CompilationContext ctx, AstElement element) {

		if (ctx.getCurrentBlock() != null) {
			ctx.getCurrentBlock().directStatement("");
			ctx.getCurrentBlock().addSingleLineComment(element.getName() + " " + element.getAttributes());
		}

		val directive = directives.get(element.getName());
		if (directive != null) {
			directive.run(ctx, element);
		}
	}

	public void processElement(CompilationContext ctx, AstElement node) {
		ctx.append("<" + node.getName());
		for (val attr : node.getAttributes().entrySet()) {
			ctx.append(" " + attr.getKey() + "='" + attr.getValue().replace("'", "\\'") + "'");
		}
		ctx.append(">");
		processList(ctx, node.getChildren());
		ctx.append("</" + node.getName() + ">");
	}

	public void processText(CompilationContext ctx, AstText node) {
		val exp = parser.parseExpression(node.getText(), ParserContext.TEMPLATE_EXPRESSION);
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
			ctx.getCurrentBlock().directStatement("ctx.writeText(" + javaExp + ");");
		}
	}

}
