package prefix;

import java.util.*;
import tk.spop.tsts.xml.XmlClassGeneratorTest.User;

import tk.spop.tsts.ExecutionContext;

public class userlist {

    public void main(ExecutionContext ctx, userlist.MainParams args) {
        ctx.writeUnparsed("\n	<div class='list'>\n		\n	</div>\n");
    }

    public static class MainParams {
        public List<User>users;
    }
}
