package tk.spop.tsts;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.helger.jcodemodel.JMod;

public class Constants {

	public static final Charset ENCODING = StandardCharsets.UTF_8;

	
	public static final String DEF_NAME_ATTRIBUTE = "name";
	public static final String DEF_PARAMS_ATTRIBUTE = "params";
	public static final String DEF_DEFAULT_NAME = "main";
	public static final String PARAMS_CLASS_SUFFIX = "Params";
	
	public static final String CONTEXT_VAR = "ctx";
	public static final String ARGS_VAR = "args";
	
	
	public static final int PUBLIC = JMod.PUBLIC;
	public static final int PUB_S = JMod.PUBLIC;
	

	
}
