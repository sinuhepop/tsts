package tk.spop.tsts.xml;

import java.io.StringReader;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import lombok.SneakyThrows;
import lombok.val;

public class XmlUtils {

	@SneakyThrows
	public static NodeList read(String content) {
		content = "<wrapper>" + content + "</wrapper>";
		val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		val source = new InputSource(new StringReader(content));
		val xml = builder.parse(source);
		return xml.getDocumentElement().getChildNodes();
	}

	public static Stream<Node> read(NodeList nodes) {
		return IntStream.range(0, nodes.getLength()).mapToObj(i -> nodes.item(i));
	}

}
