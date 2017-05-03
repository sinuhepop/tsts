package tk.spop.tsts.xml;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.val;

public class PrefixXmlAstDecider implements XmlAstDecider {

	private String elementPrefix = ":";
	private String attributePrefix = ":";

	@Override
	public Map<String, String> filterAttributes(Map<String, String> attrs, boolean dynamic) {
		val map = new LinkedHashMap<String, String>();
		attrs.keySet().forEach(k -> {
			if (k.startsWith(attributePrefix) != dynamic) {
				val key = dynamic ? k : k.substring(attributePrefix.length());
				map.put(key, attrs.get(k));
			}
		});
		return map;
	}

	@Override
	public String parseDirectiveKey(String nodeName) {
		return nodeName.startsWith(elementPrefix) ? nodeName.substring(elementPrefix.length()) : null;
	}

}
