package tk.spop.tsts.directive;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DirectiveRegistry {

	private final Map<String, Directive> map = new ConcurrentHashMap<>();

	public DirectiveRegistry add(String key, Directive directive) {
		map.put(key, directive);
		return this;
	}

	public Directive get(String key) {
		return map.get(key);
	}

	public static DirectiveRegistry getDefault() {
		return new DirectiveRegistry() //
				.add("def", new DefDirective()) //
				.add("include", new IncludeDirective());
	}
}
