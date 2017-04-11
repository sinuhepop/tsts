package tk.spop.safe.exception;

import lombok.SneakyThrows;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ThrowErrorHandler<T> implements ErrorHandler<T> {

	public static final ThrowErrorHandler INSTANCE = new ThrowErrorHandler();

	@Override
	@SneakyThrows
	public T handle(Throwable e) {
		throw e;
	}

	public static <T> ThrowErrorHandler<T> getInstance() {
		return INSTANCE;
	}
}
