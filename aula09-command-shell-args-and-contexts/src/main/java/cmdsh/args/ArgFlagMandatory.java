package cmdsh.args;

import cmdsh.core.MissingArgumentException;

public class ArgFlagMandatory extends ArgFlagOptional{
	public ArgFlagMandatory(String name){
		super(name);
	}
	@Override
	public int parse(String[] args, int from) {
		int idx = super.parse(args, from);
		if(idx == from) throw new MissingArgumentException();
		else return idx;
	}
}
