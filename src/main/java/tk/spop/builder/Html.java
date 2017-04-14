package tk.spop.builder;

public class Html {

	public static Tag tag(String name) {
		return new Tag(name);
	}

	public static Tag html() {
		return tag("html");
	}

	public static Tag meta() {
		return tag("meta");
	}

	public static Tag meta(String name, String content) {
		return meta().a("name", name).a("content", content);
	}

	public static Tag a(String href) {
		return tag("a").a("href", href);
	}

	public static Tag img(String src, String title, boolean lazy) {
		return tag("img").a((lazy ? "data-" : "") + "src", src).a("title", title);
	}
}
