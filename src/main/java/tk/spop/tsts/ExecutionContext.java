package tk.spop.tsts;

import java.io.Writer;
import java.util.Map;

import lombok.Data;
import lombok.SneakyThrows;

@Data
public class ExecutionContext {

	public static class Utils {
		public String url(String url) {
			return "";
		}
	}

	public final Utils u = new Utils();

	private Writer writer;
	private Map<String, Object> model;
	private int indentDepth;

	// print options

	public void writeAttribute(String name, Object value) {
		System.out.print(" " + name + "='" + value + "'");
	}

	public void writeText(Object text) {
		System.out.print(text);

	}

	public void writeUnparsed(Object content) {
		System.out.print(content);
	}

	@SneakyThrows
	public <T> T getTemplate(Class<T> template) {
		return template.newInstance();
	}

}
