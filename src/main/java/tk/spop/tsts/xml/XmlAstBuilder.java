package tk.spop.tsts.xml;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import lombok.val;
import tk.spop.tsts.AstBuilder;
import tk.spop.tsts.model.ast.AstNode;
import tk.spop.tsts.xml.ast.NodeProcessor;
import tk.spop.tsts.xml.ast.XmlElement;
import tk.spop.tsts.xml.ast.XmlNode;
import tk.spop.tsts.xml.ast.XmlText;

public class XmlAstBuilder implements AstBuilder, NodeProcessor {

	protected XmlAstDecider decider = new PrefixXmlAstDecider();

	@Override
	public List<AstNode> build(String content) {
		return getList(XmlUtils.read(content));
	}

	public XmlNode build(Node node) {

		if (node.getNodeType() == Node.ELEMENT_NODE) {
			val key = decider.parseDirectiveKey(node.getNodeName());
			if (key != null) {
				return new XmlElement((Element) node, this, key, true);
			}
			return new XmlElement((Element) node, this, node.getNodeName(), false);

		} else if (node.getNodeType() == Node.TEXT_NODE) {
			return new XmlText((Text) node);
		}

		return null;
	}

	public List<AstNode> getList(NodeList nodes) {
		return XmlUtils.stream(nodes) //
				.map(this::build) //
				.filter(Objects::nonNull) //
				.collect(Collectors.toList());
	}

	@Override
	public Map<String, String> getAttributes(Element node, boolean dynamic) {
		val map = node.getAttributes();
		val attrs = IntStream.range(0, map.getLength()) //
				.mapToObj(i -> map.item(i)) //
				.collect(Collectors.toMap(Node::getNodeName, Node::getNodeValue));
		return decider.filterAttributes(attrs, dynamic);
	}
}
