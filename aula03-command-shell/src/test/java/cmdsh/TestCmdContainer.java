package cmdsh;

import junit.framework.Assert;

import org.junit.Test;

import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.GitRemote;
import cmdsh.commands.GitRemoteAdd;
import cmdsh.commands.GitRemoteRm;
import cmdsh.core.CmdContainer;
import cmdsh.core.ICommand;
import cmdsh.core.UnrecognizedCommand;

public class TestCmdContainer {

	CmdContainer cnt = new CmdContainer(
			new GitAdd(),
			new GitCommit(),
			new GitRemoteAdd(), 
			new GitRemoteRm(),
			new GitRemote());

	@Test
	public void checkGitAdd(){
		ICommand c = cnt.getCommand("git add");
		Assert.assertEquals("git add", c.getName());
	}
	@Test(expected=UnrecognizedCommand.class)
	public void checkForUnknownCommand(){
		ICommand c = cnt.getCommand("git xxx");
	}
	/*
	public void checkForUnknownCommand(){
		try{
			ICommand c = cnt.getCommand("git ole");
			Assert.assertTrue(false); // Não lançou excepção
		}catch(UnrecognizedCommand e){
			Assert.assertTrue(true);
		}
	}
	 */
}
