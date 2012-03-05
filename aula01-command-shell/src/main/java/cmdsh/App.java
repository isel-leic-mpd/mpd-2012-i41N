package cmdsh;

import java.util.Scanner;

import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.GitRemote;
import cmdsh.commands.GitRemoteAdd;
import cmdsh.commands.GitRemoteRm;
import cmdsh.core.CmdContainer;
import cmdsh.core.ICommand;
import static java.lang.System.in; 
import static java.lang.System.out;

/**
 * @uml.dependency   supplier="cmdsh.commands.GitAdd"
 * @uml.dependency   supplier="cmdsh.commands.GitCommit"
 * @uml.dependency   supplier="cmdsh.commands.GitRemoteAdd"
 */
public class App {
	public static void main(String[] args) {
		//
		// Initialize and configure container
		//
		CmdContainer cnt = new CmdContainer(
				new GitAdd(),
				new GitCommit(),
				new GitRemoteAdd(), 
				new GitRemoteRm(),
				new GitRemote());
		//
		// run command shell
		//
		Scanner cin = new Scanner(in);
		out.println("****** Commmand shell application *****");
		while(true){
			out.print("> ");
			out.flush();
			String inLine = cin.nextLine();
			ICommand c = cnt.getCommand(inLine );
			c.performCommand();
		}
	}
}
