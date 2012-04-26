package cmdsh;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.GitRemoteAdd;
import cmdsh.commands.GitRemoteRm;
import cmdsh.commands.IoUnzip;
import cmdsh.commands.IoZip;
import cmdsh.core.CmdPrintTime;
import cmdsh.core.Context;
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
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		//
		// Initialize and configure container
		//
		Collection<Object> cmds = new LinkedList<Object>(); 
		File dir = new File("cmdsh\\commands");
		for (String fileName : dir.list()) {
			int idx;
			if((idx = fileName.indexOf(".class")) >= 0){
				String className = fileName.substring(0, idx);
				className = "cmdsh.commands." + className;
				Class c = Class.forName(className);
				if(ICommand.class.isAssignableFrom(c)){
					ICommand o = (ICommand) c.newInstance();
					cmds.add(new CmdPrintTime(o));
				}
			}
		}

		Context cnt = new Context( "CmdSh",cmds.toArray());
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
