package tk.spop.tml.element.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import tk.spop.tml.element.BlockElement;
import tk.spop.tml.element.Element;

@Data
public class BlockElementImpl implements BlockElement {
	private final List<Element> children = new ArrayList<>();

	public BlockElementImpl add(Element child) {
		children.add(child);
		return this;
	}
}
