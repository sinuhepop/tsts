package tk.spop.safe.safe;

import java.util.function.Supplier;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import tk.spop.safe.checked.CheckedSupplier;

@RequiredArgsConstructor
public class SafeSupplier<X> implements Supplier<X> {

	private final CheckedSupplier<X> unsafe;
	private final Class<? extends Throwable> exceptionClass;
	private final X defValue;

	@Override
	@SneakyThrows
	public X get() {
		try {
			return unsafe.get();
		} catch (Throwable e) {
			if (exceptionClass.isInstance(e)) {
				return defValue;
			}
			throw e;
		}
	}

}