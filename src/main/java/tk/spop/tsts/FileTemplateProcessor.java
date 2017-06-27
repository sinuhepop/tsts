package tk.spop.tsts;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import lombok.Setter;
import lombok.SneakyThrows;
import lombok.val;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class FileTemplateProcessor {

	private ClassGenerator generator;
	private Path templateRoot;
	private Path generatedRoot;
	private String suffix;
	private Charset charset = StandardCharsets.UTF_8;

	@SneakyThrows
	public void processAll() {
		Files.walk(templateRoot).forEach(this::process);
	}

	@SneakyThrows
	public void process(Path src) {
		val file = src.toFile();
		val name = file.getName();
		if (file.isFile() && name.endsWith(suffix)) {
			val content = new String(Files.readAllBytes(src), charset);

			val simpleName = name.substring(0, name.length() - suffix.length());
			val relativePath = templateRoot.relativize(src).resolveSibling(simpleName);
			val result = generator.generate(relativePath, content);

			val destination = generatedRoot.resolve(relativePath + ".java");
			Files.createDirectories(destination.getParent());
			Files.write(destination, result.getBytes(UTF_8));
		}
	}

}
