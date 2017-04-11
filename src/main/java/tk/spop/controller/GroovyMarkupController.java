package tk.spop.controller;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Value;

@Controller
@RequestMapping("groovy")
public class GroovyMarkupController {

	@Value
	public static class Person {
		String name;
		LocalDate birthDate;
		boolean active;
	}

	@RequestMapping("test")
	public String test(Model model) {
		model.addAttribute("a", "This is a sentence");
		model.addAttribute("b", "This is a sentence with \"double\" and 'single' quotes");
		model.addAttribute("c", "This is a sentence with <tags>");

		model.addAttribute("persons",
				Arrays.asList( //
						new Person("John Pollo", LocalDate.of(1982, 5, 26), true), //
						new Person("Pol LaGrande", LocalDate.of(1990, 3, 15), false)));
		return "test";
	}

}
