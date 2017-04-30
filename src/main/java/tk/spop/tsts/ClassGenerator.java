
package tk.spop.tsts;

import java.nio.file.Path;
import java.util.List;

import tk.spop.tsts.model.ast.AstNode;

public interface ClassGenerator {

	String generate(Path name, String content);

	void processList(CompilationContext ctx, List<AstNode> children);

}
