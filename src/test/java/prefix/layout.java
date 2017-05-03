package prefix;

import java.util.*;
import tk.spop.tsts.xml.XmlClassGeneratorTest.User;

import tk.spop.tsts.ExecutionContext;

public class layout {

    public void main(ExecutionContext ctx, layout.MainParams args) {
        String contentPath = args.contentPath;
        Map<String, Object> menuMap = args.menuMap;
        String title = args.title;
        String content = args.content;
        ctx.writeUnparsed("\n	<html>\n		<head>\n			<title>Title: ");
        ctx.writeText(title.toUpperCase());
        ctx.writeUnparsed("</title>\n			<meta></meta>\n			<link></link>\n		</head>\n		<body>\n			");
        ctx.writeText(tk.spop.tsts.util.StringUtils.capitalizeFirst(title));

        // include {src=prefix/menu}
        ctx.writeUnparsed("\n			");
        prefix.menu _template_0 = ctx.getTemplate(prefix.menu.class);
        prefix.menu.MainParams _templateArgs_0 = new prefix.menu.MainParams();
        _templateArgs_0.map = menuMap;
        _template_0.main(ctx, _templateArgs_0);

        // include {src=#footer}
        ctx.writeUnparsed("\n			<div>\n				\n			</div>\n			");
        layout _template_1 = ctx.getTemplate(layout.class);
        _template_1.footer(ctx);
        ctx.writeUnparsed("\n			<script></script>\n		</body>\n	</html>\n");

        // def {name=footer}
    }

    public void footer(ExecutionContext ctx) {
        ctx.writeUnparsed("\n\n	<div>\n		<div></div>\n	</div>\n");
    }

    public static class MainParams {
        public String contentPath;
        public Map<String, Object>menuMap;
        public String title;
        public String content;
    }
}
