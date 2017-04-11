package tk.spop.tml.writer;

import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;
import lombok.SneakyThrows;
import tk.spop.tml.element.CommentElement;
import tk.spop.tml.element.TagElement;
import tk.spop.tml.writer.XmlWriter.Context;

public class XmlWriter extends AbstractWriter<Context> {

	@Data
	public static class Context implements WriterContext {
		private final XmlWriter writer;
		private int currentIndent = 0;
	}

	private static final Map<Integer, String> spaces = Collections.synchronizedMap(new HashMap<Integer, String>());

	private boolean writeComments = true;
	private int indent = 2;

	protected String spaces(int n) {
		return spaces.computeIfAbsent(n, x -> repeat(" ", n));
	}

	@Override
	public Context newContext() {
		return new Context(this);
	}

	@Override
	@SneakyThrows
	protected void writeTagOpening(Writer writer, TagElement element, Context context) {
		writer.write(spaces(context.currentIndent));
		super.writeTagOpening(writer, element, context);
		context.currentIndent += indent;
	}

	@Override
	@SneakyThrows
	protected void writeTagEnding(Writer writer, TagElement element, Context context) {
		context.currentIndent -= indent;
		writer.write(spaces(context.currentIndent));
		super.writeTagEnding(writer, element, context);
	}

	@Override
	@SneakyThrows
	protected void writeComment(Writer writer, CommentElement element, Context context) {
		if (writeComments) {
			writer.write(spaces(context.currentIndent));
			super.writeComment(writer, element, context);
		}
	}

	protected String repeat(String s, int times) {
		return IntStream.rangeClosed(1, times).mapToObj(i -> s).collect(Collectors.joining());
	}

}
