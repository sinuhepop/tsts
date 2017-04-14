package tk.spop.builder;

import lombok.Data;

@Data
public class Text implements Node {

	private final String text;

	public String toString() {
		return '"' + text + '"';
	}
}
