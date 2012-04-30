package cmdsh.core;

import guiutil.Billboard;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class CmdPrintOnBillboard implements ICommand {
	private final static DateFormat format = new SimpleDateFormat("HH:mm:ss");
	private final static Calendar cal = Calendar.getInstance();

	
	private final ICommand cmd;
	private final Billboard bb;
	
	public CmdPrintOnBillboard(ICommand cmd, Billboard bb) {
		super();
		this.cmd = cmd;
		this.bb = bb;
	}

	@Override
	public String getName() {
		return cmd.getName();
	}

	@Override
	public void performCommand(Scanner params) {
		cmd.performCommand(params);
		bb.addText("-------------------------");
		bb.addText(format.format(cal.getTime()));
		bb.addText("performed: " + this.getName());
		
	}

	@Override
	public Iterable<IArgument> args() {
		return cmd.args();
	}
}
