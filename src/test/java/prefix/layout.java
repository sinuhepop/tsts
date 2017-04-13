package prefix;

import java.util.Map;

import tk.spop.tsts.ExecutionContext;

public class layout {

    public void main(ExecutionContext ctx, layout.MainParams args) {
    }

    public void footer(ExecutionContext ctx) {
    }

    public static class MainParams {
        public String title;
        public Map<String, Object>menuMap;
        public String contentPath;
        public String content;
    }
}
