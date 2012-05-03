package cmdsh.core;

import java.util.Collection;
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
	/** 
	 * @uml.property name="obs"
	 * @uml.associationEnd aggregation="shared" inverse="cmdsh.core.CommandObserver" multiplicity="(0 -1)" 
	 */
	private final Collection<CommandObserver> obs = new LinkedList<CommandObserver>(); 

	public void addObserver(CommandObserver o){
		obs.add(o);
	}
	
	public void removeObserver(CommandObserver o){
		obs.remove(o);
	}

	public void notifyObservers(){
		for (CommandObserver o : obs) {
			o.cmdPerformed(new CommandEvent(this.getName(), this.args(), parser));
		}
	}
	public AbstractCommand(String name, IParamsParser parser, IArgument[] arrArgs, CommandObserver...obs) {
		this.name = name;
		this.parser = parser;
		for (IArgument a : arrArgs) {args.add(a);}
		for (CommandObserver o : obs) {
			this.obs.add(o);
		}
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
		notifyObservers();
	}
	protected abstract void executeCommand();
}