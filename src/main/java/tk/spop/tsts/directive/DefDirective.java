package tk.spop.tsts.directive;

import static com.helger.jcodemodel.JMod.PUBLIC;
import static com.helger.jcodemodel.JMod.STATIC;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import lombok.SneakyThrows;
import lombok.val;
import tk.spop.tsts.CompilationContext;
import tk.spop.tsts.Constants;
import tk.spop.tsts.ExecutionContext;
import tk.spop.tsts.model.Parameter;
import tk.spop.tsts.util.StringUtils;

public class DefDirective implements Directive {

	@Override
	@SneakyThrows
	public void run(CompilationContext ctx, Element node) {

		ctx.flush();

		String name = node.getAttribute(Constants.DEF_NAME_ATTRIBUTE);
		if (name.isEmpty()) {
			name = Constants.DEF_DEFAULT_NAME;
		}

		val clss = ctx.getCurrentClass();
		val method = clss.method(PUBLIC, clss.owner().VOID, name);
		method.param(ExecutionContext.class, Constants.CONTEXT_VAR);

		val params = parseParams(node.getAttribute(Constants.DEF_PARAMS_ATTRIBUTE));
		if (!params.isEmpty()) {
			val paramClass = clss._class(PUBLIC | STATIC,
					StringUtils.capitalizeFirst(name) + Constants.PARAMS_CLASS_SUFFIX);
			for (val p : params) {
				val type = clss.owner().directClass(p.getType());
				paramClass.field(PUBLIC, type, p.getName());
			}
			method.param(paramClass, Constants.ARGS_VAR);
		}

		ctx.setCurrentBlock(method.body());
		ctx.getGenerator().processList(ctx, node.getChildNodes());

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
