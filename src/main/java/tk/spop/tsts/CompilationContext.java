package tk.spop.tsts;

import java.util.Map;
import java.util.Set;

import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JDefinedClass;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import tk.spop.tsts.model.ImportDefinition;
import tk.spop.tsts.xml.XmlClassGenerator;

@Data
public class CompilationContext {

	private XmlClassGenerator generator;

	private ImportDefinition imports;
	private Map<String, Object> attributes;
	private Set<String> variables;

	private JDefinedClass currentClass;
	private JBlock currentBlock;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private StringBuilder currentStatic = new StringBuilder();

	// print options

	public CompilationContext append(String s) {
		currentStatic.append(s);
		return this;
	}

	public void flush() {
		val txt = currentStatic.toString();
		if (!txt.isEmpty()) {
			val s = txt.replace("\"", "\\\"").replace("\n", "\\n");
			currentBlock.directStatement("ctx.writeUnparsed(\"" + s + "\");");
		}
		currentStatic = new StringBuilder();
	}
}