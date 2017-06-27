package tk.spop.tsts.spring.web;

import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import tk.spop.tsts.ExecutionContext;

@RequiredArgsConstructor
public class TstsView implements View {

	private final Class<?> template;
	private final String methodName;
	private final Object arguments;

	@Getter
	private final String contentType = "text/html";

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		run(response.getWriter());
	}

	@SneakyThrows
	public void run(Writer writer) {
		val ctx = new ExecutionContext();
		ctx.setWriter(writer);

		val templateInstance = template.newInstance();
		val method = template.getMethod(methodName, ExecutionContext.class, arguments.getClass());
		method.invoke(templateInstance, ctx, arguments);
	}

}
