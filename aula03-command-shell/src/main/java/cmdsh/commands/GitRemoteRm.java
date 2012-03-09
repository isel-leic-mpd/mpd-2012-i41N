package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.ICommand;

public class GitRemoteRm extends AbstractCommand{
	public GitRemoteRm() {
		super("git remote r,");
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git remote rm");		
	}
}
