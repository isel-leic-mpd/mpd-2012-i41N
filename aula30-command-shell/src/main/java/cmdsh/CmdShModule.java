package cmdsh;

import guiutil.Billboard;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import calc.OperatorAddFactory;
import calc.OperatorFactory;
import calc.OperatorMulFactory;
import calc.OperatorSubFactory;
import cmdsh.commands.Calculator;
import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.IoUnzip;
import cmdsh.commands.IoZip;
import cmdsh.core.CommandEvent;
import cmdsh.core.CommandObserver;
import cmdsh.core.ContextName;
import cmdsh.core.CtxCmds;
import cmdsh.core.IArgument;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

public class CmdShModule extends AbstractModule{
	@Override
	protected void configure() {

		// bind(String.class).annotatedWith(Names.named("CtxName")).toInstance("CmdSh");
		bind(String.class).annotatedWith(ContextName.class).toInstance("CmdSh");
		
		Multibinder<Object> cmdsBinder = Multibinder.newSetBinder(binder(), Object.class, CtxCmds.class);
		cmdsBinder.addBinding().to(GitAdd.class);
		cmdsBinder.addBinding().to(GitCommit.class);
		cmdsBinder.addBinding().to(IoZip.class);
		cmdsBinder.addBinding().to(IoUnzip.class);
		cmdsBinder.addBinding().to(Calculator.class);
		
		bind(CommandObserver[].class).toInstance(new CommandObserver[0]);
		
		Multibinder<OperatorFactory> opsBinder = Multibinder.newSetBinder(binder(), OperatorFactory.class);
		opsBinder.addBinding().to(OperatorAddFactory.class);
		opsBinder.addBinding().to(OperatorSubFactory.class);
		opsBinder.addBinding().to(OperatorMulFactory.class);
		
		bind(PrintStream.class).toInstance(System.out);
	}
}
