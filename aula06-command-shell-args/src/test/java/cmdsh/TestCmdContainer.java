package cmdsh;

import junit.framework.Assert;

import org.junit.Test;

import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.GitRemote;
import cmdsh.commands.GitRemoteAdd;
import cmdsh.commands.GitRemoteRm;
import cmdsh.core.CmdContainer;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;
import cmdsh.core.MissingArgumentException;
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
	@Test(expected=MissingArgumentException.class)
	public void checkGitRemoteAddMissingArgument(){
		ICommand c = cnt.getCommand("git remote add");
		c.performCommand("git remote add -f github");
	}
	@Test
	public void checkGitRemoteAddWithoutOptionals(){
		ICommand c = cnt.getCommand("git remote add");
		c.performCommand("git remote add github http://ole.com");
	}
	@Test
	public void checkGitRemoteAddWithAllargs(){
		ICommand c = cnt.getCommand("git remote add");
		c.performCommand("git remote add -f --tags github http://ole.com");
	}
	@Test
	public void checkGitAddWithAllargs(){
		ICommand c = cnt.getCommand("git add");
		c.performCommand("git add --all -f -p -v");
		for (IArgument a : c.args()) {
			Assert.assertTrue(((Boolean) a.getValue()) == true);
		}
	}
}
