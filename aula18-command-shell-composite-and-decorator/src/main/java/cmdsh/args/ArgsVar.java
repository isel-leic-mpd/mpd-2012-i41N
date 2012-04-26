package cmdsh.args;

import java.util.Iterator;

import cmdsh.core.IArgument;

public class ArgsVar implements IArgument<Iterable<String>>{
	private String[] args;
	
	@Override
	public int parse(String[] args, int from) {
		this.args = args;
		return args.length;
	}

	@Override
	public Iterable<String> getValue() {
		return new Iterable<String>() {
			public Iterator<String> iterator() {
				return new Iterator<String>() {
					int idx = 0;
					public boolean hasNext() {
						return idx < args.length;
					}
					public String next() {
						return args[idx++];
					}
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}

	@Override
	public String getName() {
		return "varargs";
	}
}
