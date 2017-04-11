package tk.spop.safe.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SwallowErrorHandler<T> implements ErrorHandler<T> {

	@Getter
	private final T defaultValue;

	@Override
	public T handle(Throwable e) {
		return defaultValue;
	}

}
