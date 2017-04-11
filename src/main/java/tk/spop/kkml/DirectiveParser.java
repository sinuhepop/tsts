package tk.spop.kkml;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.springframework.expression.spel.SpelNode;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import lombok.val;

public class DirectiveParser {

	private final Pattern regex = Pattern.compile("^\\s*#(\\w+)(.*)$", Pattern.MULTILINE);
	private final SpelExpressionParser parser = new SpelExpressionParser();

	public Directive parse(String s) {
		val m = regex.matcher(s);
		if (!m.matches()) {
			return null;
		}
		val key = m.group(1);
		val argsExp = (SpelExpression) parser.parseExpression('{' + m.group(2) + '}');
		val args = new ArrayList<SpelNode>();
		for (int i = 0; i < argsExp.getAST().getChildCount(); i++) {
			args.add(argsExp.getAST().getChild(i));
		}
		return new Directive(key, args);
	}

}
