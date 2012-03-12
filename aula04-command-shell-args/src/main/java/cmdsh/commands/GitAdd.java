package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;

public class GitAdd extends AbstractCommand{
	public GitAdd() {
		super("git add", new IArgument[]{});
	}
	@Override
	protected void executeCommand() {
		System.out.println("Performing git add");
	}

}
