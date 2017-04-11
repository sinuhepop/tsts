package tk.spop.tml.element.impl;

import java.io.Writer;

import lombok.Data;
import lombok.SneakyThrows;
import tk.spop.tml.element.DirectElement;

@Data
public class DirectElementImpl implements DirectElement {

	private final String value;

	@Override
	@SneakyThrows
	public void write(Writer writer) {
		writer.write(value);
	}

}
