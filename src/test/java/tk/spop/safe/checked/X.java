package tk.spop.safe.checked;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class X {

	private final boolean exc;
	private final int i = 1;

	public int checked() throws Exception {
		if (exc) {
			throw new Exception();
		}
		return i;
	}

	public int unchecked() {
		if (exc) {
			throw new RuntimeException();
		}
		return i;
	}

}
