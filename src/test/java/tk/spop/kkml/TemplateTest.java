package tk.spop.kkml;

import org.junit.Test;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateTest {

	private final DirectiveParser parser = new DirectiveParser();

	@Test
	public void test() {
		val d = parser.parse(" #for user, users.asList(), it ");
		log.debug("{}", d);
	}

}
