package test.prefix;

import java.util.Map;

import test.prefix.Menu.MainParams;
import tk.spop.tsts.ExecutionContext;
import tk.spop.tsts.Template;

public class Menu implements Template<MainParams> {

	public static class MainParams {
		public Map<String, Object> menu;
	}

	@Override
	public void main(ExecutionContext ctx, MainParams args) {

	}

}
