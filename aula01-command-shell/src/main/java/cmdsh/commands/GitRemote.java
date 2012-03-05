package cmdsh.commands;

import cmdsh.core.ICommand;

public class GitRemote implements ICommand{
	final String name = "git remote";
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git remote");	
	}
}
