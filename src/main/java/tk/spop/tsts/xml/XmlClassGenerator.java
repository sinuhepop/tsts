package tk.spop.tsts.xml;

import static com.helger.jcodemodel.JMod.PUBLIC;
import static com.helger.jcodemodel.JMod.STATIC;

import java.io.ByteArrayOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.w3c.dom.Element;

import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.writer.OutputStreamCodeWriter;

import lombok.SneakyThrows;
import lombok.val;
import tk.spop.tsts.ClassGenerator;
import tk.spop.tsts.Constants;
import tk.spop.tsts.ExecutionContext;
import tk.spop.tsts.model.Parameter;
import tk.spop.tsts.util.StringUtils;

public class XmlClassGenerator implements ClassGenerator {
	
	

	@Override
	@SneakyThrows
	public String generate(Path path, String content) {

		val nodes = XmlUtils.read(content);

		val cm = new JCodeModel();
		val pkg = cm._package(path.getParent().toString());
		val clss = pkg._class(path.getFileName().toString());
		

		XmlUtils.read(nodes) //
				.flatMap(n -> n instanceof Element ? Stream.of((Element) n) : Stream.empty()) //
				.forEach(el -> generateMethod(clss, el));

		return getSource(clss);
	}

	@SneakyThrows
	protected String getSource(JDefinedClass clss) {
		val os = new ByteArrayOutputStream();
		val writer = new OutputStreamCodeWriter(os, Constants.ENCODING);
		clss.getPackage().owner().build(writer);
		return os.toString(Constants.ENCODING.displayName());
	}

	@SneakyThrows
	protected void generateMethod(JDefinedClass clss, Element def) {

		String name = def.getAttribute(Constants.DEF_NAME_ATTRIBUTE);
		if (name.isEmpty()) {
			name = Constants.DEF_DEFAULT_NAME;
		}

		val method = clss.method(PUBLIC, clss.owner().VOID, name);
		method.param(ExecutionContext.class, Constants.CONTEXT_VAR);

		val params = parseParams(def.getAttribute(Constants.DEF_PARAMS_ATTRIBUTE));
		if (!params.isEmpty()) {
			val paramClass = clss._class(PUBLIC | STATIC,
					StringUtils.capitalizeFirst(name) + Constants.PARAMS_CLASS_SUFFIX);
			for (val p : params) {
				val type = clss.owner().directClass(p.getType());
				paramClass.field(PUBLIC, type, p.getName());
			}
			method.param(paramClass, Constants.ARGS_VAR);
		}
	}

	protected List<Parameter> parseParams(String params) {
		val list = new ArrayList<Parameter>();
		if (!params.isEmpty()) {
			for (String p : params.split("\\|")) {
				p = p.trim();
				val i = p.lastIndexOf(' ');
				val type = p.substring(0, i).replace('[', '<').replace(']', '>');
				val name = p.substring(i);
				list.add(new Parameter(type.trim(), name.trim()));
			}
		}
		return list;
	}

}
