package tk.spop.tml.element.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import tk.spop.tml.element.Element;
import tk.spop.tml.element.TagElement;

@Data
public class TagElementImpl implements TagElement {

	private final String name;
	private final Map<String, String> attributes = new LinkedHashMap<>();
	private final List<Element> children = new ArrayList<>();

	public TagElementImpl attr(String key, String value) {
		attributes.put(key, value);
		return this;
	}

	public TagElementImpl add(Element child) {
		children.add(child);
		return this;
	}

}
