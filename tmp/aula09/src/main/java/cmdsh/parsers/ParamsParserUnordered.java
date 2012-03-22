package cmdsh.parsers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import collect.IterUtils;
import collect.Predicate;

import cmdsh.core.IArgument;
import cmdsh.core.IParamsParser;

public class ParamsParserUnordered implements IParamsParser{

	@Override
	public void parse(Scanner params, Iterable<IArgument> args) {
		List<String> paramsList = new LinkedList<String>();
		while(params.hasNext()) paramsList.add(params.next());
		String [] aParams = paramsList.toArray(new String[paramsList.size()]);
		for (final IArgument a : args) {
			int idx = IterUtils.findIndex(paramsList, new Predicate<String>() {public boolean invoke(String item) {
					return a.getName().equals(item);
				}				
			});
			if(idx >= 0) a.parse(aParams, idx);
		}
	}

}
