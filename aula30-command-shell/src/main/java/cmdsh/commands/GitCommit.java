package cmdsh.commands;

import com.google.inject.Inject;

import cmdsh.core.AbstractCommand;
import cmdsh.core.CommandObserver;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;
import cmdsh.parsers.ParamsParserOrdered;

public class GitCommit extends AbstractCommand{
	
	@Inject
	public GitCommit(CommandObserver...obs) {
		super("git commit", new  ParamsParserOrdered(), new IArgument[]{}, obs);
	}
	@Override
	public void executeCommand() {
		System.out.println("Performing git commit");
	}
}
