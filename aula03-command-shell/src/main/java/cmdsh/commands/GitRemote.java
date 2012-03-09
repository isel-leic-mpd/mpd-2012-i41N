package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.ICommand;

public class GitRemote extends AbstractCommand{
	public GitRemote() {
		super("git remote");
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git remote");	
	}
}
