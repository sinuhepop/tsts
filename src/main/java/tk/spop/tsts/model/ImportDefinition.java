package tk.spop.tsts.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImportDefinition {

	private final List<String> importList;

	public ImportDefinition() {
		this(new ArrayList<>());
	}

	@Override
	public String toString() {
		return importList.stream() //
				.map(def -> "import " + def + ";") //
				.collect(Collectors.joining("\n"));
	}

	public ImportDefinition add(String def, boolean isStatic) {
		importList.add((isStatic ? "static " : "") + def.replace('$', '.'));
		return this;
	}

	public ImportDefinition add(Class<?> c) {
		return add(c.getName(), false);
	}

	public ImportDefinition add(Package p) {
		return add(p.getName() + ".*", false);
	}

	public ImportDefinition clone() {
		return new ImportDefinition(new ArrayList<>(importList));
	}

}
