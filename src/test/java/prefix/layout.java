package prefix;

import java.util.*;
import tk.spop.tsts.xml.XmlClassGeneratorTest.User;

import tk.spop.tsts.ExecutionContext;

public class layout {

    public void main(ExecutionContext ctx, layout.MainParams args) {
        ctx.writeUnparsed("\n	<html>\n		<head>\n			<title>Title: ");
        ctx.writeText(args.title.toUpperCase());
        ctx.writeUnparsed(" ***</title>\n			<meta name='title' value='#{title}'></meta>\n			<link href='#{u:url(\"rsc/styles.css\")}'></link>\n		</head>\n		<body>\n			\n			<div id='main'>\n				\n			</div>\n			\n			<script src='#{u:url(\'rsc/scripts.js\')}'></script>\n		</body>\n	</html>\n");
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
