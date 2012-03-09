package cmdsh.args;

import cmdsh.core.BadArgumentException;
import cmdsh.core.IArgument;

public class ArgPairNameValue implements IArgument<String>{
	final String name;
	String value;
	public ArgPairNameValue(String name){
		this.name = name;
	}
	
	@Override
	public int parse(String[] args, int from) {
		if(args[from].equals(name)){
			if(++from >= args.length) throw new BadArgumentException();
			value = args[from];
			return from + 1;
		}
		else{
			return from;
		}
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	

}
