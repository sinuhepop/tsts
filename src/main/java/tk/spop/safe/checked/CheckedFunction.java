package tk.spop.safe.checked;

import java.util.function.Function;

import tk.spop.safe.exception.ErrorHandler;

@FunctionalInterface
public interface CheckedFunction<T, R> {

	R apply(T t) throws Throwable;

	default Function<T, R> withHandler(ErrorHandler<R> handler) {
		return (T t) -> {
			try {
				return apply(t);
			} catch (Throwable e) {
				return handler.handle(e);
			}
		};
	}

}
