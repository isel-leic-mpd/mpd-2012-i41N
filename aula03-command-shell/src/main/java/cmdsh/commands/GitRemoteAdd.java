package cmdsh.commands;

import cmdsh.args.ArgFlagOptional;
import cmdsh.args.ArgWithoutName;
import cmdsh.core.AbstractCommand;

public class GitRemoteAdd extends AbstractCommand{
	public GitRemoteAdd() {
		super("git remote add");
		args.add(new ArgFlagOptional("-f"));
		args.add(new ArgFlagOptional("--tags"));
		args.add(new ArgWithoutName("name"));
		args.add(new ArgWithoutName("url"));
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git remote add");		
	}

}
