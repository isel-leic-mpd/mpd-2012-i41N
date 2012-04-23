package cmdsh.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CmdAdapter implements ICommand{
	private final Method cmd;
	private final Object target;
	private final String name;
	/**
	 * The argument 'o' represents a command with a 
	 * public method with the name performCommand. 
	 */
	public CmdAdapter(Object o){
		try {
			Method[] cmds = o.getClass().getMethods();
			for (Method m : cmds) {
				CommandMethod anot = m.getAnnotation(CommandMethod.class);
				if(anot != null){
					cmd = m;
					name = anot.name();
					if((m.getModifiers() & Modifier.STATIC) != 0 )
						target = null;
					else 
						target = o;
					return;
				}
			}
			throw new IllegalCommandFormatException();

		} catch (SecurityException e) {
			throw new IllegalCommandFormatException();
		}
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void performCommand(String params) {
		try {
			cmd.invoke(target);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public Iterable<IArgument> args() {
		return null;
	} 

}
