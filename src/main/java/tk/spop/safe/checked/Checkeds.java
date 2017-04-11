package tk.spop.safe.checked;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import tk.spop.safe.exception.ThrowErrorHandler;

public class Checkeds {

	public static <T, R> Function<T, R> uncheckFunction(CheckedFunction<T, R> checked) {
		return checked.withHandler(ThrowErrorHandler.getInstance());
	}

	public static <T> Supplier<T> uncheckSupplier(CheckedSupplier<T> checked) {
		return checked.withHandler(ThrowErrorHandler.getInstance());
	}

	public static <T> Consumer<T> uncheckConsumer(CheckedConsumer<T> checked) {
		return checked.withHandler(ThrowErrorHandler.getInstance());
	}

}
