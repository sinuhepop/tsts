package tk.spop.safe.checked;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import lombok.val;

public class CheckedsTest {

	@Test
	public void noThrow() {
		val list = Collections.nCopies(2, new X(false));
		assertEquals(asList(1, 1), list.stream().map(X::unchecked).collect(toList()));
		assertEquals(asList(1, 1), list.stream().map(Checkeds.uncheckFunction(X::checked)).collect(toList()));
	}

	@Test(expected = RuntimeException.class)
	public void unchecked() {
		val list = Collections.nCopies(2, new X(true));
		list.stream().map(X::unchecked).collect(toList());
	}

	@Test(expected = Exception.class)
	public void checked() {
		val list = Collections.nCopies(2, new X(true));
		list.stream().map(Checkeds.uncheckFunction(X::checked)).collect(toList());
	}

}
