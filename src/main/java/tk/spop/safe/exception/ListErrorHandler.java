package tk.spop.safe.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListErrorHandler<T> implements ErrorHandler<T> {

	private final List<Throwable> list = new ArrayList<>();
	private final T defaultValue;

	@Override
	public T handle(Throwable e) {
		list.add(e);
		return defaultValue;
	}

	public boolean hasErrors() {
		return !list.isEmpty();
	}

}
