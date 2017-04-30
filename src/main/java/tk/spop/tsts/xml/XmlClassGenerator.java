package tk.spop.tsts.xml;

import java.util.List;

import tk.spop.tsts.AbstractClassGenerator;
import tk.spop.tsts.model.ast.AstNode;
import tk.spop.tsts.xml.ast.XmlNodeBuilder;

public class XmlClassGenerator extends AbstractClassGenerator {

	protected XmlNodeBuilder builder = new XmlNodeBuilder();

	@Override
	protected List<AstNode> getNodes(String content) {
		return builder.build(XmlUtils.read(content));
	}

}
