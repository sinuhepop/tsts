package prefix;

import java.util.*;
import tk.spop.tsts.xml.XmlClassGeneratorTest.User;

import tk.spop.tsts.ExecutionContext;

public class menu {

    public void main(ExecutionContext ctx, menu.MainParams args) {
        ctx.writeUnparsed("\n	\n	<div class='menu'>\n		\n	</div>\n");
    }

    public static class MainParams {
        public Map<String, Object>map;
    }
}
