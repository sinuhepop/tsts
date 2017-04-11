package tk.spop.tml;

import java.util.List;
import java.util.Map;

public interface Tag<T extends Tag<T>> {

	String name();

	Map<String, String> attributes();

	List<T> children();

}
