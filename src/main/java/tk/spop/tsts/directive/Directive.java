package tk.spop.tsts.directive;

import org.w3c.dom.Element;

import tk.spop.tsts.CompilationContext;

public interface Directive {

	void run(CompilationContext ctx, Element node);

}
