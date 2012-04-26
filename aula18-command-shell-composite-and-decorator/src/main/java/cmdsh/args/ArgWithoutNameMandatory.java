package cmdsh.args;

import collect.Predicate;
import cmdsh.core.IArgument;
import cmdsh.core.MissingArgumentException;

public class ArgWithoutNameMandatory implements IArgument {
	private final String name;
	private String value;
	public ArgWithoutNameMandatory(String n) {
		name = n;
	}

	@Override
	public int parse(String[] params, int from) {
		if(params.length <= from) throw new MissingArgumentException();
		value = params[from];
		return from + 1;
	}
	@Override
	public Object getValue() {
		return value;
	}
	public static <T> T find(Iterable<T> elems, Predicate<T> p){
		for (T item : elems) {
			if(p.invoke(item))
				return item;
		}
		return null;
	}
	@Override
	public String getName() {
		return name;
	}
}
