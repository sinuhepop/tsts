package tk.spop.safe.checked;

import java.util.function.Consumer;

import tk.spop.safe.exception.ErrorHandler;

@FunctionalInterface
public interface CheckedConsumer<T> {

	void accept(T t) throws Throwable;

	default Consumer<T> withHandler(ErrorHandler<T> handler) {
		return (T t) -> {
			try {
				accept(t);
			} catch (Throwable e) {
				handler.handle(e);
			}
		};
	}

}
