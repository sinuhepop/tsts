package tk.spop.safe.checked;

import java.util.function.Supplier;

import tk.spop.safe.exception.ErrorHandler;

@FunctionalInterface
public interface CheckedSupplier<T> {

	T get() throws Throwable;

	default Supplier<T> withHandler(ErrorHandler<T> handler) {
		return () -> {
			try {
				return get();
			} catch (Throwable e) {
				return handler.handle(e);
			}
		};
	}

}
