package tk.spop.tsts.xml;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.tool.EclipseCompiler;
import org.junit.Test;

import lombok.SneakyThrows;
import lombok.val;
import prefix.layout;
import tk.spop.tsts.Constants;
import tk.spop.tsts.ExecutionContext;
import tk.spop.tsts.directive.DirectiveRegistry;
import tk.spop.tsts.model.ImportDefinition;

public class XmlClassGeneratorTest {

	public static class User {

	}

	private final Path source = Paths.get("src/test/resources");
	private final Path dest = Paths.get("src/test/java");

	private final XmlClassGenerator generator = new XmlClassGenerator();

	public XmlClassGeneratorTest() {
		val imports = new ImportDefinition();
		imports.add(Map.class.getPackage());
		imports.add(User.class);
		generator.setImports(imports);
		generator.setDirectives(DirectiveRegistry.getDefault());
	}

	@Test
	@SneakyThrows
	public void compileAll() {
		Files.newDirectoryStream(source.resolve("prefix")).forEach(path -> {
			val relPath = source.relativize(path).toString();
			val noExtension = relPath.substring(0, relPath.lastIndexOf('.'));
			compile(Paths.get(noExtension));
		});

		val compiler = new EclipseCompiler(); // ToolProvider.getSystemJavaCompiler();
		val res = compiler.run(null, null, null, dest.resolve("prefix").toString(), "-1.8", "-warn:none", "-verbose");

		System.out.println(res);
	}

	@Test
	@SneakyThrows
	public void layout() {
		val layout = new layout();
		val args  = new layout.MainParams();
		args.title="cuc";
		layout.main(new ExecutionContext(), args);
	}

	@SneakyThrows
	protected void compile(Path path) {
		val xmlPath = source.resolve(path + ".xml");
		val xmlContent = new String(Files.readAllBytes(xmlPath), Constants.ENCODING);
		val javaContent = generator.generate(path, xmlContent);

		val destination = dest.resolve(path + ".java");
		Files.createDirectories(destination.getParent());
		Files.write(destination, javaContent.getBytes(Constants.ENCODING));
	}
}