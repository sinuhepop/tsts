package tk.spop.safe.checked;

import tk.spop.safe.exception.ErrorHandler;

public interface Checked<T> {

	T withHandler(ErrorHandler<T> handler);
}
