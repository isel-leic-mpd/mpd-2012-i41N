package cmdsh.commands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cmdsh.args.ArgFlagOptional;
import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;
import cmdsh.parsers.ParamsParserOrdered;
import cmdsh.parsers.ParamsParserUnordered;

public class GitAddPrintTime extends GitAdd{
	DateFormat format = new SimpleDateFormat("HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	
	public GitAddPrintTime() {
		super("git add2");
	}
	@Override
	protected void executeCommand() {
		super.executeCommand();
		System.out.println(format.format(cal.getTime()));
	}

}
