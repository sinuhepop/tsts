package tk.spop.builder;

import lombok.SneakyThrows;
import lombok.val;

public class Writer {

	public String toString(Node node) {
		val sb = new StringBuilder();
		writeTo(sb, node);
		return sb.toString();
	}

	public void writeTo(Appendable a, Node node) {
		writeTo(a, node, "");
	}

	@SneakyThrows
	protected void writeTo(Appendable a, Node node, String indent) {
		a.append(indent).append(node.toString());
		if (node instanceof Tag) {
			for (val c : ((Tag) node).getChildren()) {
				a.append('\n');
				writeTo(a, c, indent + "    ");
			}
		}
	}

}
