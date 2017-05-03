package tk.spop.tsts.xml.ast;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import tk.spop.tsts.model.ast.AstNode;

public interface NodeProcessor {

	List<AstNode> getList(NodeList nodes);

	Map<String, String> getAttributes(Element node, boolean dynamic);
}
