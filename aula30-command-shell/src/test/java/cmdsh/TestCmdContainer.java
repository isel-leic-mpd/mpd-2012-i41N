package cmdsh;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import junit.framework.Assert;

import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.multibindings.Multibinder;

import cmdsh.commands.Calculator;
import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.GitRemoteAdd;
import cmdsh.commands.GitRemoteRm;
import cmdsh.commands.IoUnzip;
import cmdsh.commands.IoZip;
import cmdsh.core.CommandMethod;
import cmdsh.core.CommandObserver;
import cmdsh.core.Context;
import cmdsh.core.ContextName;
import cmdsh.core.CtxCmds;
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

	ByteArrayOutputStream out = new ByteArrayOutputStream();
	
	Context cnt = Guice.createInjector(new AbstractModule(){
		@Override
		protected void configure() {
			bind(String.class).annotatedWith(ContextName.class).toInstance("CmdSh");
			
			Multibinder<Object> cmdsBinder = Multibinder.newSetBinder(binder(), Object.class, CtxCmds.class);
			cmdsBinder.addBinding().to(GitAdd.class);
			/*
			cmdsBinder.addBinding().to(GitCommit.class);
			cmdsBinder.addBinding().to(GitRemoteAdd.class);
			cmdsBinder.addBinding().to(GitRemoteRm.class);
			cmdsBinder.addBinding().to(CmdDummy.class);
			*/
			bind(CommandObserver[].class).toInstance(new CommandObserver[0]);
			PrintStream print = new PrintStream(out);
			bind(PrintStream.class).toInstance(print);
			
		}		
	}).getInstance(Context.class);
	
	static final String NEW_LINE = System.getProperty("line.separator");
	
	@Test
	public void checkGitAdd(){
		cnt.performCommand(new Scanner("git add"));
		Assert.assertEquals("Performing git add" + NEW_LINE, out.toString());
	}
	@Test
	public void checkGitAddWithAllargs(){
		/*
		Context c = cnt.get("git add");
		c.getCommand().performCommand("git add -p --all -v -f ");
		for (IArgument a : c.getCommand().args()) {
			Assert.assertTrue(((Boolean) a.getValue()) == true);
		}
		*/
	}

	/*
	@Test
	public void checkCmdDummy(){
		Context c = cnt.get("dummyXpto");
		Assert.assertEquals("dummyXpto", c.getCommand().getName());
	}

	@Test(expected=UnrecognizedContext.class)
	public void checkForUnknownCommand(){
		Context c = cnt.get("git xxx");
	}
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
	*/
}
