package tk.spop.tsts.directive;

import lombok.SneakyThrows;
import lombok.val;
import tk.spop.tsts.CompilationContext;
import tk.spop.tsts.model.ast.AstElement;
import tk.spop.tsts.util.StringUtils;

public class IncludeDirective implements Directive {

	@Override
	@SneakyThrows
	public void run(CompilationContext ctx, AstElement element) {

		ctx.flush();

		val templateVariable = ctx.newVariable("template");
		val src = element.getAttributes().get("src");

		val macoqui = src.indexOf('#');
		val template = macoqui == 0 ? ctx.getCurrentClass().name() //
				: (macoqui > 0 ? src.substring(0, macoqui) //
						: src).replace('/', '.');
		val method = macoqui >= 0 ? src.substring(macoqui + 1) : DefDirective.DEFAULT_NAME;

		ctx.direct(template + " " + templateVariable + " = ctx.getTemplate(" + template + ".class);");

		val args = element.getDynamicAttributes();
		if (args.isEmpty()) {
			ctx.direct(templateVariable + "." + method + "(ctx);");

		} else {
			val argsVariable = ctx.newVariable("templateArgs");
			val argsType = template + "." + StringUtils.capitalizeFirst(method) + DefDirective.PARAMS_CLASS_SUFFIX;
			ctx.direct(argsType + " " + argsVariable + " = new " + argsType + "();");
			for (val arg : args.entrySet()) {
				ctx.direct(argsVariable + "." + arg.getKey() + " = " + arg.getValue() + ";");
			}
			ctx.direct(templateVariable + "." + method + "(ctx, " + argsVariable + ");");
		}
	}

}
