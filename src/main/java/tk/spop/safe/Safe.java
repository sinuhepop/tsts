package tk.spop.safe;

import java.util.function.Supplier;

import tk.spop.safe.checked.CheckedSupplier;

public class Safe {

	public <T> Supplier<T> safe(CheckedSupplier<T> unsafe) {
		return null;
		// return new SafeSupplier<>(unsafe);
	}

}
