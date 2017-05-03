package tk.spop.tsts.xml.ast;

import org.w3c.dom.Text;

import lombok.RequiredArgsConstructor;
import tk.spop.tsts.model.ast.AstText;

@RequiredArgsConstructor
public class XmlText implements XmlNode, AstText {

	private final Text node;

	@Override
	public String getText() {
		return node.getTextContent();
	}

	@Override
	public boolean isExpression() {
		return false;
	}

}
