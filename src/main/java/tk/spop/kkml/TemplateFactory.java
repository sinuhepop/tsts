package tk.spop.kkml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;

public class TemplateFactory {

	private final Map<String, Template> cache = Collections.synchronizedMap(new HashMap<>());

	private final List<ResourceLoader> loaders = new ArrayList<>();

	@SneakyThrows
	public Template get(String path) {
		Template t = cache.get(path);
		if (t == null) {
			val rsc = find(path);
			if (rsc != null) {
				@Cleanup
				val is = rsc.getInputStream();
				val doc = read(is);
			}
		}
		return t;
	}

	protected Resource find(String path) {
		return loaders.stream() //
				.map(x -> x.getResource(path)) //
				.filter(x -> x != null) //
				.findFirst().orElse(null);
	}

	@SneakyThrows
	public static final Document read(InputStream is) {
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	}

}
