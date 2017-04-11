package tk.spop.tml.element;

public interface CommentElement extends Element {

	@Override
	default ElementType getType() {
		return ElementType.COMMENT;
	}

	String value();

}
