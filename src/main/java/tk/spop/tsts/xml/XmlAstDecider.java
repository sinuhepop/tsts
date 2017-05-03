package tk.spop.tsts.xml;

import java.util.Map;

public interface XmlAstDecider {

	Map<String, String> filterAttributes(Map<String, String> attrs, boolean dynamic);

	String parseDirectiveKey(String nodeName);

}
