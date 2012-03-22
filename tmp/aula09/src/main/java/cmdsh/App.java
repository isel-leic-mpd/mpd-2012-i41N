package cmdsh;

import java.util.Scanner;

import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.GitRemoteAdd;
import cmdsh.commands.GitRemoteRm;
import cmdsh.commands.IoUnzip;
import cmdsh.commands.IoZip;
import cmdsh.core.CmdContainer;
import cmdsh.core.Context;
import cmdsh.core.ICommand;
import static java.lang.System.in; 
import static java.lang.System.out;

/**
 * @uml.dependency   supplier="cmdsh.commands.GitAdd"
 * @uml.dependency   supplier="cmdsh.commands.GitCommit"
 * @uml.dependency   supplier="cmdsh.commands.GitRemoteAdd"
 * @uml.dependency   supplier="cmdsh.core.ICommand"
 */
public class App {
	public static void main(String[] args) {
		//
		// Initialize and configure container
		//
		Context cnt = new Context("CmdSh",
				new GitAdd(),
				new GitCommit(),
				new GitRemoteAdd(), 
				new GitRemoteRm(),
				new IoZip(),
				new IoUnzip());
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
