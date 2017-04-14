package tk.spop.builder;

import static tk.spop.builder.Html.a;
import static tk.spop.builder.Html.html;
import static tk.spop.builder.Html.img;
import static tk.spop.builder.Html.meta;
import static tk.spop.builder.Html.tag;

import org.junit.Test;

import lombok.val;

public class HtmlTest {

	@Test
	public void test() {
		val title = "El pollo";
		val page = html().add( //
				meta("title", title), //
				tag("title").add(title), //
				a("www.elpollo.com").add("El pollo"), //
				img("www.elpollo.com/rsc/img/logo.gif", "El pollo", false) //
		);
		System.out.println(new Writer().toString(page));
	}

}
