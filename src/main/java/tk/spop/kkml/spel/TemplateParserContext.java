package tk.spop.kkml.spel;

import org.springframework.expression.ParserContext;

import lombok.Data;

@Data
public class TemplateParserContext implements ParserContext {

	private final boolean template = true;
	private final String expressionPrefix;
	private final String expressionSuffix;

}