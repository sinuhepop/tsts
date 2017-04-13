package tk.spop.kkml;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;

import lombok.SneakyThrows;
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

	@Test
	@SneakyThrows
	public void readXml() {
		val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new FileInputStream(new File("src/test/resources/prefix/layout.xml")));
	}

}
