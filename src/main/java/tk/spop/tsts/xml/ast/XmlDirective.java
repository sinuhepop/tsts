package tk.spop.tsts.xml.ast;

import org.w3c.dom.Element;

import lombok.Getter;

public class XmlDirective extends XmlElement {

	@Getter
	private final String name;

	public XmlDirective(Element element, XmlNodeBuilder builder, String name) {
		super(element, builder);
		this.name = name;
	}

	@Override
	public boolean isDirective() {
		return true;
	}

}
