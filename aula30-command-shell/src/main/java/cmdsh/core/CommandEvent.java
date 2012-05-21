package cmdsh.core;

public class CommandEvent {
	public final String cmdName;
	public final Iterable<IArgument> args;
	public final IParamsParser parser;
	public CommandEvent(String cmdName, Iterable<IArgument> args, IParamsParser parser) {
		super();
		this.cmdName = cmdName;
		this.args = args;
		this.parser = parser;
	}
	
	
}
