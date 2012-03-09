package cmdsh.args;

import cmdsh.core.IArgument;

public class ArgFlagOptional implements IArgument<Boolean>{
	final String name;
	boolean value;
	
	public ArgFlagOptional(String name){
		this.name = name;
		this.value = false;
	}
	@Override
	public int parse(String[] args, int from) {
		if(args[from].equals(name)){
			value = true;
			return from + 1;
		}
		else
			return from;
	}
	@Override
	public Boolean getValue() {
		return value;
	}

}
