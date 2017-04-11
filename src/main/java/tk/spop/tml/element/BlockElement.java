package tk.spop.tml.element;

import java.util.List;

public interface BlockElement extends Element {

	@Override
	default ElementType getType() {
		return ElementType.BLOCK;
	}

	List<Element> children();
}
