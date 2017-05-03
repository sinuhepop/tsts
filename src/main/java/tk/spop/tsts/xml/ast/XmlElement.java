package tk.spop.tsts.xml.ast;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tk.spop.tsts.model.ast.AstElement;
import tk.spop.tsts.model.ast.AstNode;

@RequiredArgsConstructor
public class XmlElement implements XmlNode, AstElement {

	private final Element element;
	private final NodeProcessor processor;

	@Getter
	private final String name;

	@Getter
	private final boolean directive;

	@Getter(lazy = true)
	private final Map<String, String> attributes = processor.getAttributes(element, false);

	@Getter(lazy = true)
	private final Map<String, String> dynamicAttributes = processor.getAttributes(element, true);

	@Override
	public List<AstNode> getChildren() {
		return processor.getList(element.getChildNodes());
	}

}
