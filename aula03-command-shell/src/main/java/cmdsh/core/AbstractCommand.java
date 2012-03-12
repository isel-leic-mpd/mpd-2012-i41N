package cmdsh.core;

import java.util.LinkedList;
import java.util.List;


public abstract class AbstractCommand implements ICommand{
	final String name;
	
	/** 
	 * @uml.property name="args"
	 * @uml.associationEnd aggregation="shared" inverse="cmdsh.core.IArgument" multiplicity="(0 -1)" 
	 */
	protected final List<IArgument> args = new LinkedList<IArgument>();

	public AbstractCommand(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

}