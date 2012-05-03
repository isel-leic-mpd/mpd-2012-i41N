package cmdsh;

import guiutil.Billboard;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import calc.IntExpression;
import calc.Operator;
import calc.OperatorAddFactory;
import calc.OperatorFactory;
import calc.OperatorMulFactory;
import calc.OperatorSubFactory;
import cmdsh.commands.Calculator;
import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.GitRemoteAdd;
import cmdsh.commands.GitRemoteRm;
import cmdsh.commands.IoUnzip;
import cmdsh.commands.IoZip;
import cmdsh.core.CmdPrintOnBillboard;
import cmdsh.core.CmdPrintTime;
import cmdsh.core.CommandEvent;
import cmdsh.core.CommandObserver;
import cmdsh.core.Context;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;
import cmdsh.core.UnrecognizedContext;
import static java.lang.System.in; 
import static java.lang.System.out;

/**
 * @uml.dependency   supplier="cmdsh.commands.GitAdd"
 * @uml.dependency   supplier="cmdsh.commands.GitCommit"
 * @uml.dependency   supplier="cmdsh.commands.GitRemoteAdd"
 * @uml.dependency   supplier="cmdsh.core.ICommand"
 */
public class App {
	private final static DateFormat format = new SimpleDateFormat("HH:mm:ss");
	private final static Calendar cal = Calendar.getInstance();


	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		//
		// Initialize and configure container
		//
		final Billboard bb = Billboard.launch();
		CommandObserver o1 = new CommandObserver() {
			public void cmdPerformed(CommandEvent e) {
				System.out.println(format.format(cal.getTime()) + "parser = " + e.parser );
			}
		};
		CommandObserver o2 = new CommandObserver() {
			public void cmdPerformed(CommandEvent e) {
				bb.addText("-------------------------");
				bb.addText(format.format(cal.getTime()));
				String cmdName = e.cmdName + "(";
				for (IArgument a : e.args) {
					cmdName += a.getName() + ",";
				}
				cmdName += ")";
				bb.addText("performed: " + cmdName);
				
			}
		};

		Context cnt = new Context( "CmdSh",
				new GitAdd(o1),
				new GitCommit(o2), 
				new IoZip(o2),
				new IoUnzip(),
				new Calculator(Arrays.asList(new OperatorFactory[]{
						new OperatorAddFactory(),
						new OperatorSubFactory(),
						new OperatorMulFactory()
				}), o1, o2)
		);
		//
		// run command shell
		//
		Scanner cin = new Scanner(in);
		out.println("****** Commmand shell application *****");
		while(true){
			out.print("> ");
			out.flush();
			String inLine = cin.nextLine();
			cnt.performCommand(new Scanner(inLine));
			
		}
	}
}
