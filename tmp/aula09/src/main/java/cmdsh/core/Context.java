package cmdsh.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Context implements ICommand{
	private final Map<String, ICommand> ctxs;
	private final String name;

	public Context(String name) {
		this.ctxs = new HashMap<String, ICommand>();
		this.name = name;
	}
	public Context(String name, ICommand...cmds) {
		this.ctxs = new HashMap<String, ICommand>();
		this.name = name;
		for (ICommand c : cmds) {
			add(c);
		}
	}
	public void add(ICommand c){
		add(c, new Scanner(c.getName()));
	}

	private void add(ICommand newCmd, Scanner s){
		String ctxName = s.next();
		ICommand childCmd = ctxs.get(ctxName);
		if(childCmd == null){
			if(s.hasNext()){
				Context childCtx = new Context(ctxName);
				this.ctxs.put(ctxName, childCtx);
				childCtx.add(newCmd, s);
			}else{
				ctxs.put(ctxName, newCmd);
			}
		}else{
			((Context)childCmd).add(newCmd, s);
		}
	}

	@Override
	public void performCommand(Scanner s){
		if(s.hasNext()){
			ICommand c = ctxs.get(s.next());
			if(c != null){
				c.performCommand(s);
				return;
			}
		}
		for (String n : ctxs.keySet()) {
			System.out.println(n);
		}

	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public Iterable<IArgument> args() {
		throw new UnsupportedOperationException();
	}
}
