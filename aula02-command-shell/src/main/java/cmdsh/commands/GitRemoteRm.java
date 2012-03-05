package cmdsh.commands;

import cmdsh.core.ICommand;

public class GitRemoteRm implements ICommand{
	final String name = "git remote rm";
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git remote rm");		
	}
}
