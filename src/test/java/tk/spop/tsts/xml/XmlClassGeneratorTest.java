package tk.spop.tsts.xml;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.Test;

import lombok.SneakyThrows;
import lombok.val;
import tk.spop.tsts.DefaultClassGenerator;
import tk.spop.tsts.FileTemplateProcessor;
import tk.spop.tsts.directive.DirectiveRegistry;
import tk.spop.tsts.model.ImportDefinition;

public class XmlClassGeneratorTest {

	public static class User {

	}

	private final Path source = Paths.get("src/test/resources");
	private final Path dest = Paths.get("src/test/java");

	private final FileTemplateProcessor processor;

	public XmlClassGeneratorTest() {

		val generator = new DefaultClassGenerator();
		val imports = new ImportDefinition();
		imports.add(Map.class.getPackage());
		imports.add(User.class);
		generator.setImports(imports);
		generator.setDirectives(DirectiveRegistry.getDefault());
		generator.setBuilder(new XmlAstBuilder());

		processor = new FileTemplateProcessor() //
				.setGenerator(generator) //
				.setTemplateRoot(source) //
				.setGeneratedRoot(dest) //
				.setSuffix(".xml");
	}

	@Test
	@SneakyThrows
	public void compileAll() {
		processor.processAll();

		// val compiler = new EclipseCompiler();
		// val compiler = ToolProvider.getSystemJavaCompiler();
		// val res = compiler.run(null, null, null,
		// dest.resolve("prefix").toString(), "-1.8", "-warn:none", "-verbose");

		// System.out.println(res);
	}

}