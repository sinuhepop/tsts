package tk.spop.tsts.xml.ast;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import lombok.val;
import tk.spop.tsts.model.ast.AstNode;
import tk.spop.tsts.xml.XmlUtils;

public class XmlNodeBuilder {

	public XmlNode build(Node node) {

		if (node.getNodeType() == Node.ELEMENT_NODE) {
			val key = parseDirectiveKey(node.getNodeName());
			if (key != null) {
				return new XmlDirective((Element) node, this, key);
			}
			return new XmlElement((Element) node, this);

		} else if (node.getNodeType() == Node.TEXT_NODE) {
			return new XmlText((Text) node);
		}

		return null;
	}

	public List<AstNode> build(NodeList nodes) {
		return XmlUtils.stream(nodes) //
				.map(this::build) //
				.filter(Objects::nonNull) //
				.collect(Collectors.toList());
	}

	protected String parseDirectiveKey(String nodeName) {
		return nodeName.startsWith(":") ? nodeName.substring(1) : null;
	}

}
