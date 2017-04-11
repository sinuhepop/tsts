package tk.spop.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import lombok.val;

public class Initializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext ctx) throws ServletException {

		val springCtx = new AnnotationConfigWebApplicationContext();
//		springCtx.register(Config.class);

		val servlet = ctx.addServlet("spring", new DispatcherServlet(springCtx));
		servlet.addMapping("/*");
		servlet.setLoadOnStartup(1);
	}

}
