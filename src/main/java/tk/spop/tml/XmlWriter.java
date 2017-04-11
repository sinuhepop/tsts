package tk.spop.tml;

import java.io.Writer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class XmlWriter implements TagWriter {

	private  int identation;

	@Override
	public void write(Tag<?> tag, Writer writer) {
		
		

	}

	@Override
	public void write(Tag<?> tag, Writer writer, WriterContext context) {
		// TODO Auto-generated method stub
		
	}
	

}
