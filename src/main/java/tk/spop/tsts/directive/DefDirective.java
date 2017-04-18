package tk.spop.tsts.directive;

import static com.helger.jcodemodel.JMod.PUBLIC;
import static com.helger.jcodemodel.JMod.STATIC;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import lombok.val;
import tk.spop.tsts.CompilationContext;
import tk.spop.tsts.Constants;
import tk.spop.tsts.ExecutionContext;
import tk.spop.tsts.model.Parameter;
import tk.spop.tsts.model.ast.AstElement;
import tk.spop.tsts.util.StringUtils;

public class DefDirective implements Directive {

	@Override
	@SneakyThrows
	public void run(CompilationContext ctx, AstElement element) {

		ctx.flush();

		val attrs = element.getAttributes();

		String name = attrs.get(Constants.DEF_NAME_ATTRIBUTE);
		if (name.isEmpty()) {
			name = Constants.DEF_DEFAULT_NAME;
		}

		val clss = ctx.getCurrentClass();
		val method = clss.method(PUBLIC, clss.owner().VOID, name);
		method.param(ExecutionContext.class, Constants.CONTEXT_VAR);
		val body = method.body();

		val params = parseParams(attrs.get(Constants.DEF_PARAMS_ATTRIBUTE));
		if (!params.isEmpty()) {

			val paramClass = clss._class(PUBLIC | STATIC,
					StringUtils.capitalizeFirst(name) + Constants.PARAMS_CLASS_SUFFIX);
			for (val p : params) {
				val type = clss.owner().directClass(p.getType());
				paramClass.field(PUBLIC, type, p.getName());

				// TODO: no direct statements
				body.directStatement(
						p.getType() + " " + p.getName() + " = " + Constants.ARGS_VAR + "." + p.getName() + ";");
			}
			method.param(paramClass, Constants.ARGS_VAR);
		}

		ctx.setCurrentBlock(body);
		ctx.getGenerator().processList(ctx, element.getChildren());

		ctx.flush();
	}

	protected List<Parameter> parseParams(String params) {
		val list = new ArrayList<Parameter>();
		if (!params.isEmpty()) {
			for (String p : params.split("\\|")) {
				p = p.trim();
				val i = p.lastIndexOf(' ');
				val type = p.substring(0, i).replace('[', '<').replace(']', '>');
				val name = p.substring(i);
				list.add(new Parameter(type.trim(), name.trim()));
			}
		}
		return list;
	}
}
