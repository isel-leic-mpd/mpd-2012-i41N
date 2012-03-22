package cmdsh.parsers;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import cmdsh.core.IArgument;
import cmdsh.core.IParamsParser;

public class ParamsParserOrdered implements IParamsParser{

	@Override
	public void parse(Scanner params, Iterable<IArgument> args) {
		List<String> paramsList = new LinkedList<String>();
		while(params.hasNext()) paramsList.add(params.next());
		String [] aParams = paramsList.toArray(new String[paramsList.size()]);
		int idx = 0;
		for (IArgument a : args) {
			idx = a.parse(aParams, idx);
		}
	}

}
