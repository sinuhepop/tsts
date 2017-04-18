package tk.spop.tsts.xml.ast;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tk.spop.tsts.model.ast.AstElement;
import tk.spop.tsts.model.ast.AstNode;
import tk.spop.tsts.xml.XmlUtils;

@RequiredArgsConstructor
public class XmlElement implements XmlNode, AstElement {

	private final Element element;
	private final XmlNodeBuilder builder;

	@Getter(lazy = true)
	private final String name = element.getNodeName();

	@Getter(lazy = true)
	private final Map<String, String> attributes = XmlUtils.getAttributes(element);

	@Override
	public List<AstNode> getChildren() {
		return builder.build(element.getChildNodes());
	}

	public boolean isDirective() {
		return false;
	}

}
