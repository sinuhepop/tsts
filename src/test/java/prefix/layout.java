package prefix;

import java.util.*;
import tk.spop.tsts.xml.XmlClassGeneratorTest.User;

import tk.spop.tsts.ExecutionContext;

public class layout {

    public void main(ExecutionContext ctx, layout.MainParams args) {
        String title = args.title;
        Map<String, Object> menuMap = args.menuMap;
        String contentPath = args.contentPath;
        String content = args.content;
        ctx.writeUnparsed("\n	<html>\n		<head>\n			<title>Title: ");
        ctx.writeText(title.toUpperCase());
        ctx.writeUnparsed(" ***</title>\n			<meta name='title' value='#{title}'></meta>\n			<link href='#{u:url(\"rsc/styles.css\")}'></link>\n		</head>\n		<body>\n			");
        ctx.writeText(tk.spop.tsts.util.StringUtils.capitalizeFirst(title));

        // include {_map=menuMap, src=prefix/menu}
        ctx.writeUnparsed("\n			");
        prefix.menu _template_0 = ctx.getTemplate(prefix.menu.class);
        prefix.menu.MainParams _templateArgs_0 = new prefix.menu.MainParams();
        _templateArgs_0.map = menuMap;
        _template_0.main(ctx, _templateArgs_0);

        // include {src=#footer}
        ctx.writeUnparsed("\n			<div id='main'>\n				\n			</div>\n			");
        layout _template_1 = ctx.getTemplate(layout.class);
        _template_1.footer(ctx);
        ctx.writeUnparsed("\n			<script src='#{u:url(\'rsc/scripts.js\')}'></script>\n		</body>\n	</html>\n");

        // def {name=footer}
        ctx.writeUnparsed("\n");
    }

    public void footer(ExecutionContext ctx) {
        ctx.writeUnparsed("\n	<div class='footer'>\n		<div></div>\n	</div>\n");
    }

    public static class MainParams {
        public String title;
        public Map<String, Object>menuMap;
        public String contentPath;
        public String content;
    }
}
