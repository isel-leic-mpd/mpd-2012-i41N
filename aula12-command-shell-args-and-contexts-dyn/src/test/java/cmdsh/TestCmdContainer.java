package cmdsh;

import junit.framework.Assert;

import org.junit.Test;

import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.GitRemoteAdd;
import cmdsh.commands.GitRemoteRm;
import cmdsh.core.CommandMethod;
import cmdsh.core.Context;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;
import cmdsh.core.MissingArgumentException;
import cmdsh.core.UnrecognizedContext;

class CmdDummy{
	@CommandMethod(name = "dummyXpto")
	public static void performCommand(){
		System.out.println("Performing Dummy...");
	}
}

public class TestCmdContainer {

	Context cnt = new Context("CmdSh",
			new GitAdd(),
			new GitCommit(),
			new GitRemoteAdd(), 
			new GitRemoteRm(),
			new CmdDummy());
	@Test
	public void checkCmdDummy(){
		Context c = cnt.get("dummyXpto");
		Assert.assertEquals("dummyXpto", c.getCommand().getName());
	}

	@Test
	public void checkGitAdd(){
		Context c = cnt.get("git add");
		Assert.assertEquals("git add", c.getCommand().getName());
	}
	@Test(expected=UnrecognizedContext.class)
	public void checkForUnknownCommand(){
		Context c = cnt.get("git xxx");
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
		Context c = cnt.get("git remote add");
		c.getCommand().performCommand("git remote add -f github");
	}
	@Test
	public void checkGitRemoteAddWithoutOptionals(){
		Context  c = cnt.get("git remote add");
		c.getCommand().performCommand("git remote add github http://ole.com");
	}
	@Test
	public void checkGitRemoteAddWithAllargs(){
		Context c = cnt.get("git remote add");
		c.getCommand().performCommand("git remote add -f --tags github http://ole.com");
	}
	@Test
	public void checkGitAddWithAllargs(){
		Context c = cnt.get("git add");
		c.getCommand().performCommand("git add -p --all -v -f ");
		for (IArgument a : c.getCommand().args()) {
			Assert.assertTrue(((Boolean) a.getValue()) == true);
		}
	}
}
