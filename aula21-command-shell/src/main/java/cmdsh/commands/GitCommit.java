package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.CommandObserver;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;
import cmdsh.parsers.ParamsParserOrdered;

public class GitCommit extends AbstractCommand{
	public GitCommit(CommandObserver...obs) {
		super("git commit", new  ParamsParserOrdered(), new IArgument[]{}, obs);
	}
	@Override
	public void executeCommand() {
		System.out.println("Performing git commit");
	}
}
