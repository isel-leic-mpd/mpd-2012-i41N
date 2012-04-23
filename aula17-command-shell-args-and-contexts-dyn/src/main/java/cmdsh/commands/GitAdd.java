package cmdsh.commands;

import cmdsh.args.ArgFlagOptional;
import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;
import cmdsh.parsers.ParamsParserOrdered;
import cmdsh.parsers.ParamsParserUnordered;

public class GitAdd extends AbstractCommand{
	protected GitAdd(String name) {
		super(name, new  ParamsParserUnordered(), new IArgument[]{
			new ArgFlagOptional("--all"),
			new ArgFlagOptional("-f"),
			new ArgFlagOptional("-p"),
			new ArgFlagOptional("-v")
		});
	}
	public GitAdd() {
		this("git add");
	}
	@Override
	protected void executeCommand() {
		System.out.println("Performing git add");
	}

}
