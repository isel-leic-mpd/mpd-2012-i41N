package cmdsh.args;

import cmdsh.core.IArgument;
import cmdsh.core.MissingArgumentException;

public class ArgWithoutNameOptional implements IArgument {
	private final String name;
	private String value;
	public ArgWithoutNameOptional(String n) {
		name = n;
	}

	@Override
	public int parse(String[] params, int from) {
		if(params.length > from){
			value = params[from];
			from++;
		}
		return from;
	}
	@Override
	public Object getValue() {
		return value;
	}

}
