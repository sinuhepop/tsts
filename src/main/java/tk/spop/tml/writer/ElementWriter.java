package tk.spop.tml.writer;

import java.io.Writer;

import tk.spop.tml.element.Element;

public interface ElementWriter<C extends WriterContext> {

	C newContext();

	void write(Writer writer, Element element, C context);

}
