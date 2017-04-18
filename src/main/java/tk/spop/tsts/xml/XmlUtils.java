package tk.spop.tsts.xml;

import java.io.StringReader;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import lombok.SneakyThrows;
import lombok.val;

public class XmlUtils {

	private static final String UNDERSCORE = "_";

	@SneakyThrows
	public static NodeList read(String content) {
		content = "<wrapper>" + content + "</wrapper>";
		val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		val source = new InputSource(new StringReader(content));
		val xml = builder.parse(source);
		return xml.getDocumentElement().getChildNodes();
	}

	public static Stream<Node> stream(NodeList nodes) {
		return IntStream.range(0, nodes.getLength()).mapToObj(i -> nodes.item(i));
	}

	public static Map<String, String> getAttributes(Element node) {
		return getAttributes(node, false);
	}

	public static Map<String, String> getAttributes(Element node, boolean underscore) {
		val map = node.getAttributes();
		return IntStream.range(0, map.getLength()) //
				.mapToObj(i -> map.item(i)) //
				.filter(n -> !underscore || n.getNodeName().startsWith(UNDERSCORE)) //
				.collect(Collectors.toMap(//
						n -> n.getNodeName().substring(underscore ? 1 : 0), //
						Node::getNodeValue));
	}

}
