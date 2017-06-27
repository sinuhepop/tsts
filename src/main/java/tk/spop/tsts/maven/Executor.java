package tk.spop.tsts.maven;

import java.nio.file.Paths;

import lombok.val;
import tk.spop.tsts.DefaultClassGenerator;
import tk.spop.tsts.FileTemplateProcessor;
import tk.spop.tsts.directive.DirectiveRegistry;
import tk.spop.tsts.model.ImportDefinition;
import tk.spop.tsts.xml.XmlAstBuilder;

public class Executor {

	public static void main(String... args) {

		val gen = new DefaultClassGenerator();
		val imports = new ImportDefinition();
		gen.setImports(imports);
		gen.setDirectives(DirectiveRegistry.getDefault());
		gen.setBuilder(new XmlAstBuilder());

		val processor = new FileTemplateProcessor() //
				.setGenerator(gen) //
				.setTemplateRoot(Paths.get(args[0])) //
				.setGeneratedRoot(Paths.get(args[1])) //
				.setSuffix(".xml");
		processor.processAll();
	}

}
