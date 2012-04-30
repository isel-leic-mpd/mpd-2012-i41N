package cmdsh.parsers;

import cmdsh.core.IArgument;
import cmdsh.core.IParamsParser;

public class ParamsParserOrdered implements IParamsParser{

	@Override
	public void parse(String params, Iterable<IArgument> args) {
		String [] aParams = params.trim().split(" ");
		int idx = 0;
		for (IArgument a : args) {
			idx = a.parse(aParams, idx);
		}
	}

}
