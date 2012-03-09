package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.ICommand;

public class GitCommit extends AbstractCommand{
	public GitCommit() {
		super("git commit");
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git commit");
	}
}
