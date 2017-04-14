package tk.spop.builder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.val;

@Data
public class Tag implements Node {

	private final String name;
	private final Map<String, Object> attrs = new LinkedHashMap<>();
	private final List<Node> children = new ArrayList<>();

	public Tag a(String name, Object value) {
		attrs.put(name, value);
		return this;
	}

	public Tag add(Tag tag) {
		children.add(tag);
		return this;
	}

	public Tag add(String text) {
		children.add(new Text(text));
		return this;
	}

	public Tag add(Object... children) {
		for (val c : children) {
			if (c instanceof Tag) {
				add((Tag) c);
			} else if (c != null) {
				add(c.toString());
			}
		}
		return this;
	}

	public String toString() {
		return name + (attrs.isEmpty() ? "" : attrs) + '[' + children.size() + ']';
	}

}
