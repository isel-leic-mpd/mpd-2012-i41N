package cmdsh.parsers;

import java.util.Arrays;

import collect.IterUtils;
import collect.Predicate;

import cmdsh.core.IArgument;
import cmdsh.core.IParamsParser;

public class ParamsParserUnordered implements IParamsParser{

	@Override
	public void parse(String params, Iterable<IArgument> args) {
		String [] aParams = params.split(" ");
		Iterable<String> paramsList = Arrays.asList(aParams);
		for (final IArgument a : args) {
			int idx = IterUtils.findIndex(paramsList, new Predicate<String>() {public boolean invoke(String item) {
					return a.getName().equals(item);
				}				
			});
			if(idx >= 0) a.parse(aParams, idx);
		}
	}

}
