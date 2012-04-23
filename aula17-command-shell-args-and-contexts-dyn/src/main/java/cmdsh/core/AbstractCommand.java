package cmdsh.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractCommand implements ICommand{
	final String name;
	
	/** 
	 * @uml.property name="parser"
	 * @uml.associationEnd aggregation="shared"
	 */
	final IParamsParser parser;
	
	/** 
	 * @uml.property name="args"
	 * @uml.associationEnd aggregation="shared" inverse="cmdsh.core.IArgument" multiplicity="(0 -1)" 
	 */
	private final List<IArgument> args = new LinkedList<IArgument>();

	public AbstractCommand(String name, IParamsParser parser, IArgument[] arrArgs) {
		this.name = name;
		this.parser = parser;
		for (IArgument a : arrArgs) {args.add(a);}
	}
	public String getName() {
		return name;
	}
	public Iterable<IArgument> args(){
		return Collections.unmodifiableCollection(args);
	}
	@Override
	public final void performCommand(Scanner params) {
		if(params.hasNext()) 
			parser.parse(params.nextLine(), args);
		else
			parser.parse("", args);
		executeCommand();
	}
	protected abstract void executeCommand();
}