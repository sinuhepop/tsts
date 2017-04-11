package tk.spop.tml;

import java.io.Writer;

public interface TagWriter {

	void write(Tag<?> tag, Writer writer);

	void write(Tag<?> tag, Writer writer, WriterContext context);

}
