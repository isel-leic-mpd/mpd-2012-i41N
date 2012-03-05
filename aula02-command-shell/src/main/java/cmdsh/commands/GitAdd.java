package cmdsh.commands;

import cmdsh.core.ICommand;

public class GitAdd implements ICommand{
	final String name = "git add";
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git add");
	}

}
