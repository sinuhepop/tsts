package tk.spop.tsts.directive;

import tk.spop.tsts.CompilationContext;
import tk.spop.tsts.model.ast.AstElement;

public interface Directive {

	void run(CompilationContext ctx, AstElement node);

}
