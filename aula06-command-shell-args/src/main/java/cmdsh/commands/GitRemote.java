package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;
import cmdsh.parsers.ParamsParserOrdered;

public class GitRemote extends AbstractCommand{
	public GitRemote() {
		super("git remote", new  ParamsParserOrdered(), new IArgument[]{});
	}
	@Override
	public void executeCommand() {
		System.out.println("Performing git remote");	
	}
}
