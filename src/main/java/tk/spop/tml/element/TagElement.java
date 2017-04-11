package tk.spop.tml.element;

import java.util.List;
import java.util.Map;

public interface TagElement extends Element {

	@Override
	default ElementType getType() {
		return ElementType.TAG;
	}

	String name();

	Map<String, String> attributes();

	List<Element> children();
}
