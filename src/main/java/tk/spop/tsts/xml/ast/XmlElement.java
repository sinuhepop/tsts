package tk.spop.tsts.xml.ast;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.w3c.dom.Element;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tk.spop.tsts.model.ast.AstElement;
import tk.spop.tsts.model.ast.AstNode;
import tk.spop.tsts.xml.XmlUtils;

@RequiredArgsConstructor
public class XmlElement implements XmlNode, AstElement {

	private final Element element;

	@Getter
	private final boolean directive;

	@Getter(lazy = true)
	private final String name = element.getNodeName();

	@Getter(lazy = true)
	private final Map<String, String> attributes = XmlUtils.getAttributes(element);

	@Override
	public List<AstNode> getChildren() {
		return XmlUtils.stream(element.getChildNodes()) //
				.map(XmlNode::wrap) //
				.filter(Objects::nonNull) //
				.collect(Collectors.toList());
	}

}
