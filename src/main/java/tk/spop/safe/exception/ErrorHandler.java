package tk.spop.safe.exception;

public interface ErrorHandler<T> {

	T handle(Throwable e);

}
