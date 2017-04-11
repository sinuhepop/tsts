package tk.spop.tml.element;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ElementType {

	TAG(TagElement.class), //
	COMMENT(CommentElement.class), //
	DIRECT(DirectElement.class), //
	BLOCK(BlockElement.class);

	@Getter
	private final Class<? extends Element> type;

}
