package tk.spop.tml.writer;

import java.io.Writer;

import lombok.SneakyThrows;
import tk.spop.tml.element.CommentElement;
import tk.spop.tml.element.DirectElement;
import tk.spop.tml.element.Element;
import tk.spop.tml.element.TagElement;
import tk.spop.tml.element.BlockElement;

public abstract class AbstractWriter<C extends WriterContext> implements ElementWriter<C> {

	@Override
	public void write(Writer writer, Element element, C context) {
		switch (element.getType()) {
		case TAG:
			writeTag(writer, (TagElement) element, context);
			break;
		case COMMENT:
			writeComment(writer, (CommentElement) element, context);
			break;
		case DIRECT:
			writeDirect(writer, (DirectElement) element, context);
			break;
		case BLOCK:
			writeVirtual(writer, (BlockElement) element, context);
			break;
		}
	}

	@SneakyThrows
	protected void writeTag(Writer writer, TagElement element, C context) {
		writeTagOpening(writer, element, context);
		element.children().forEach(e -> write(writer, e, context));
		writeTagEnding(writer, element, context);
	}

	@SneakyThrows
	protected void writeTagOpening(Writer writer, TagElement element, C context) {
		writer.write('<');
		writer.write(element.name());
		element.attributes().forEach((k, v) -> writeTagAttribute(writer, k, v, context));
		writer.write('>');
	}

	@SneakyThrows
	protected void writeTagEnding(Writer writer, TagElement element, C context) {
		writer.write("</");
		writer.write(element.name());
		writer.write('>');
	}

	@SneakyThrows
	protected void writeTagAttribute(Writer writer, String name, String value, C context) {
		writer.write(' ');
		writer.write(name);
		writer.write('=');
		writer.write('"');
		writer.write(value); // TODO: encode
		writer.write('"');
	}

	@SneakyThrows
	protected void writeComment(Writer writer, CommentElement element, C context) {
		writer.write("<!-- ");
		writer.write(element.value()); // TODO: encode
		writer.write(" --!>");
	}

	protected void writeDirect(Writer writer, DirectElement element, C context) {
		element.write(writer);
	}

	protected void writeVirtual(Writer writer, BlockElement element, C context) {
		element.children().forEach(e -> write(writer, e, context));
	}
}
