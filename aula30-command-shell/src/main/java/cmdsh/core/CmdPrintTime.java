package cmdsh.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class CmdPrintTime implements ICommand {
	private final static DateFormat format = new SimpleDateFormat("HH:mm:ss");
	private final static Calendar cal = Calendar.getInstance();

	
	private final ICommand cmd;
	
	public CmdPrintTime(ICommand cmd) {
		super();
		this.cmd = cmd;
	}

	@Override
	public String getName() {
		return cmd.getName();
	}

	@Override
	public void performCommand(Scanner params) {
		cmd.performCommand(params);
		System.out.println(format.format(cal.getTime()));		
	}

	@Override
	public Iterable<IArgument> args() {
		return cmd.args();
	}
}
