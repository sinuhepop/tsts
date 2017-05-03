package tk.spop.tsts.directive;

import static com.helger.jcodemodel.JMod.PUBLIC;
import static com.helger.jcodemodel.JMod.STATIC;

import lombok.SneakyThrows;
import lombok.val;
import tk.spop.tsts.CompilationContext;
import tk.spop.tsts.Constants;
import tk.spop.tsts.ExecutionContext;
import tk.spop.tsts.model.ast.AstElement;
import tk.spop.tsts.util.StringUtils;

public class DefDirective implements Directive {

	public static final String NAME_ATTRIBUTE = "name";
	public static final String DEFAULT_NAME = "main";

	public static final String PARAMS_CLASS_SUFFIX = "Params";

	@Override
	@SneakyThrows
	public void run(CompilationContext ctx, AstElement element) {

		// ctx.flush();

		val name = element.getAttributes().getOrDefault(NAME_ATTRIBUTE, DEFAULT_NAME);

		val clss = ctx.getCurrentClass();
		val method = clss.method(PUBLIC, clss.owner().VOID, name);
		method.param(ExecutionContext.class, Constants.CONTEXT_VAR);
		val body = method.body();

		val params = element.getDynamicAttributes();
		if (!params.isEmpty()) {

			val paramClass = clss._class(PUBLIC | STATIC, StringUtils.capitalizeFirst(name) + PARAMS_CLASS_SUFFIX);
			for (val p : params.entrySet()) {
				val typeString = p.getValue().replace('[', '<').replace(']', '>');
				val type = clss.owner().directClass(typeString);
				paramClass.field(PUBLIC, type, p.getKey());

				// TODO: no direct statements
				body.directStatement(typeString + " " + p.getKey() + " = " + Constants.ARGS_VAR + "." + p.getKey() + ";");
			}
			method.param(paramClass, Constants.ARGS_VAR);
		}

		ctx.setCurrentBlock(body);
		ctx.getGenerator().processList(ctx, element.getChildren());

		ctx.flush();
	}
}
