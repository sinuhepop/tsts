package test.prefix;

import java.util.Map;

import lombok.val;
import test.prefix.Layout.MainParams;
import tk.spop.tsts.ExecutionContext;
import tk.spop.tsts.Template;

public class Layout implements Template<MainParams> {

	public static class MainParams {
		public String title;
		public Map<String, Object> menuMap;
		public String contentPath;
		public String content;
	}

	@Override
	public void main(ExecutionContext ctx, MainParams args) {

		// Arguments
		String title = args.title;
		Map<String, Object> menuMap = args.menuMap;
		String contentPath = args.contentPath;
		String content = args.content;

		ctx.writeUnparsed("<html><head><title>");
		ctx.writeText(title);
		ctx.writeUnparsed("</title><meta name='title'");
		ctx.writeAttribute("value", title);
		ctx.writeUnparsed("/><link");
		ctx.writeAttribute("href", ctx.u.url("rsc/styles.css"));
		ctx.writeUnparsed("></link></head><body>");

		// include menu
		val _templ1 = ctx.getTemplate(Menu.class);
		val _templ1_args = new Menu.MainParams();
		_templ1_args.menu = menuMap;
		_templ1.main(ctx, _templ1_args);

		ctx.writeUnparsed("<div id='main'>");

		// include contentPath
//		val _templ1 = ctx.getTemplate(Menu.class);
//		val _templ1_args = new Menu.MainParams();
//		_templ1_args.menu = menuMap;
//		_templ1.main(ctx, _templ1_args);

		ctx.writeUnparsed("</div>");

		// include footer
		footer(ctx);

		ctx.writeUnparsed("<script");
		ctx.writeAttribute("src", ctx.u.url("rsc/scripts.js"));
		ctx.writeUnparsed("></script></body><html>");
	}

	public void footer(ExecutionContext ctx) {
		ctx.writeUnparsed("<div class='footer'><div/></div>");
	}

}
