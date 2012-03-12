package cmdsh.core;

import java.util.LinkedList;
import java.util.List;


public abstract class AbstractCommand implements ICommand{
	final String name;
	
	/** 
	 * @uml.property name="args"
	 * @uml.associationEnd aggregation="shared" inverse="cmdsh.core.IArgument" multiplicity="(0 -1)" 
	 */
	private final List<IArgument> args = new LinkedList<IArgument>();

	public AbstractCommand(String name, IArgument[] arrArgs) {
		this.name = name;
		for (IArgument a : arrArgs) {args.add(a);}
	}
	public String getName() {
		return name;
	}
	private void parseParams(String params) {
		params = params.substring(getName().length() + 1);
		String [] aParams = params.split(" ");
		int idx = 0;
		for (IArgument a : args) {
			idx = a.parse(aParams, idx);
		}
	}
	@Override
	public final void performCommand(String params) {
		parseParams(params);
		executeCommand();
	}
	protected abstract void executeCommand();
}