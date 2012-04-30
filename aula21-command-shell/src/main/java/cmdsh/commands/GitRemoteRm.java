package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;
import cmdsh.parsers.ParamsParserOrdered;

public class GitRemoteRm extends AbstractCommand{
	public GitRemoteRm() {
		super("git remote rm", new  ParamsParserOrdered(), new IArgument[]{});
	}
	@Override
	public void executeCommand() {
		System.out.println("Performing git remote rm");		
	}
}
