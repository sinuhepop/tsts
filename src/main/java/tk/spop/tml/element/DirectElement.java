package tk.spop.tml.element;

import java.io.Writer;

public interface DirectElement extends Element {

	@Override
	default ElementType getType() {
		return ElementType.DIRECT;
	}

	void write(Writer writer);
}
