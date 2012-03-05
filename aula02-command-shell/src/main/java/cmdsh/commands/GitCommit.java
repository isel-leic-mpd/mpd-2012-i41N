package cmdsh.commands;

import cmdsh.core.ICommand;

public class GitCommit implements ICommand{
	final String name = "git commit";
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git commit");
	}
}
