package tk.spop.tsts.xml;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import lombok.SneakyThrows;
import lombok.val;
import tk.spop.tsts.Constants;

public class XmlClassGeneratorTest {

	private final Path source = Paths.get("src/test/resources");
	private final Path dest = Paths.get("src/test/java");

	@Test
	@SneakyThrows
	public void layout() {
		compile(Paths.get("prefix/layout"));
	}

	@SneakyThrows
	protected void compile(Path path) {
		val xmlPath = source.resolve(path + ".xml");
		val xmlContent = new String(Files.readAllBytes(xmlPath), Constants.ENCODING);
		val javaContent = new XmlClassGenerator().generate(path, xmlContent);

		val destination = dest.resolve(path + ".java");
		Files.createDirectories(destination.getParent());
		Files.write(destination, javaContent.getBytes(Constants.ENCODING));
	}
}