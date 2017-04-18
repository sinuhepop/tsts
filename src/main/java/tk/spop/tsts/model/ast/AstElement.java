package tk.spop.tsts.model.ast;

import java.util.List;
import java.util.Map;

public interface AstElement extends AstNode {

	String getName();

	Map<String, String> getAttributes();

	List<AstNode> getChildren();

	boolean isDirective();

}
