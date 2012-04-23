package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;
import cmdsh.parsers.ParamsParserOrdered;

public class GitCommit extends AbstractCommand{
	public GitCommit() {
		super("git commit", new  ParamsParserOrdered(), new IArgument[]{});
	}
	@Override
	public void executeCommand() {
		System.out.println("Performing git commit");
	}
}
