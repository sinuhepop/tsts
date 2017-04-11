package tk.spop.kkml;

import java.util.List;

import org.springframework.expression.spel.SpelNode;

import lombok.Data;

@Data
public class Directive {

	private final String key;
	private final List<SpelNode> args;

}
