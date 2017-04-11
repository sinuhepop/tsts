package tk.spop.tml;

public interface HtmlTag extends Tag<HtmlTag> {
	
	String id();
	HtmlTag id(String id);

}
