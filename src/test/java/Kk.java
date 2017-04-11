import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.junit.Test;

import lombok.SneakyThrows;
import lombok.val;

public class Kk {

	public static interface A {

		default int x() {
			return 1;
		}
	}

	public static interface B {

		default int x() {
			return 2;
		}
	}

	public static class AB implements A, B {

		@Override
		public int x() {
			return A.super.x();
		}

	}

	@Test
	@SneakyThrows
	public void repos() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/test/resources/repos.txt"))) {
			val obj = in.readObject();
			System.out.println(obj);
		}
	}

}
