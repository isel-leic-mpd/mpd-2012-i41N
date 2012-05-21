package cmdsh.commands;

import java.io.PrintStream;

import com.google.inject.Inject;

import cmdsh.args.ArgFlagOptional;
import cmdsh.core.AbstractCommand;
import cmdsh.core.CommandObserver;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;
import cmdsh.parsers.ParamsParserOrdered;
import cmdsh.parsers.ParamsParserUnordered;

public class GitAdd extends AbstractCommand{
	final PrintStream out;
	
	protected GitAdd(CommandObserver...obs) {
		this(System.out, obs);
	}
	
	@Inject
	protected GitAdd(PrintStream out, CommandObserver...obs) {
		super("git add", new  ParamsParserUnordered(), new IArgument[]{
			new ArgFlagOptional("--all"),
			new ArgFlagOptional("-f"),
			new ArgFlagOptional("-p"),
			new ArgFlagOptional("-v")
		}, obs);
		this.out = out;
	}
	
	@Override
	protected void executeCommand() {
		out.println("Performing git add");
	}

}
