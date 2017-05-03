package tk.spop.tsts;

import java.util.List;

import tk.spop.tsts.model.ast.AstNode;

public interface AstBuilder {

	List<AstNode> build(String content);

}
