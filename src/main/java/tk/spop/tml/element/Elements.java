package tk.spop.tml.element;

import java.io.StringWriter;

import lombok.val;
import tk.spop.tml.writer.XmlWriter;

public class Elements {

	public static String toString(Element el) {
		val sw = new StringWriter();
		val w = new XmlWriter();
		w.write(sw, el, w.newContext());
		return sw.toString();
	}

}
