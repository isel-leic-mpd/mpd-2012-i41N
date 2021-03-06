package cmdsh.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Context implements ICommand{
	/**
	 * @uml.associationEnd  aggregation="shared" inverse="cmdsh.core.ICommand" multiplicity="(0 -1)" 
	 */
	private final Map<String, ICommand> ctxs;
	private String name;

	public Context(String name, Object...cmds) {
		this.name = name;
		this.ctxs = new HashMap<String, ICommand>();
		for (Object o : cmds) {
			if(ICommand.class.isAssignableFrom(o.getClass()))
				this.add((ICommand)o);
			else
				this.add(new CmdAdapter(o));
		}
	}
	public String getName(){
		return name;
	}	
	public void add(ICommand c){
		add(c, new Scanner(c.getName()));
	}
	private void add(ICommand c, Scanner s){
		if(s.hasNext()){
			String ctxName = s.next();
			Context ctx = (Context) ctxs.get(ctxName);
			if(s.hasNext()){
				if(ctx == null){
					ctx = new Context(ctxName);
					ctxs.put(ctxName, ctx);
				}
				ctx.add(c, s);
			}else{
				ctxs.put(ctxName, c);
			}
		}
	}
	@Override
	public void performCommand(Scanner params){
		if(params.hasNext()){
			ICommand c = ctxs.get(params.next());
			if(c != null){
				c.performCommand(params);
			}else{
				throw new UnrecognizedContext();
			}
		}
		else{
			for (ICommand c : ctxs.values()) {
				System.out.println(c.getName());
			}
		}
	}
	@Override
	public Iterable<IArgument> args() {
		throw new UnsupportedOperationException();
	}
}
