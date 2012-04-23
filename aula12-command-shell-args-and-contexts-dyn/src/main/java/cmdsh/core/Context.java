package cmdsh.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Context {
	/**
	 * @uml.associationEnd  aggregation="shared" inverse="cmdsh.core.Context" multiplicity="(0 -1)" 
	 */
	private final Map<String, Context> ctxs;
	/** 
	 * @uml.property name="cmd"
	 * @uml.associationEnd aggregation="shared"
	 */
	private ICommand cmd;
	private String name;

	public Context(String name, Object...cmds) {
		this.name = name;
		this.ctxs = new HashMap<String, Context>();
		for (Object o : cmds) {
			if(ICommand.class.isAssignableFrom(o.getClass()))
				this.add((ICommand)o);
			else
				this.add(new CmdAdapter(o));
		}
	}
	public ICommand getCommand(){
		return cmd;
	}
	public String getName(){
		return name;
	}
	public Iterable<Context> getSubContexts(){
		return Collections.unmodifiableCollection(ctxs.values());
	}
	
	public void add(ICommand c){
		add(c, new Scanner(c.getName()));
	}
	private void add(ICommand c, Scanner s){
		if(s.hasNext()){
			String ctxName = s.next();
			Context ctx = ctxs.get(ctxName);
			if(ctx == null){
				ctx = new Context(ctxName);
				ctxs.put(ctxName, ctx);
			}
			ctx.add(c, s);
		}else{
			cmd = c;
		}
	}

	public Context get(String params){
		return get(new Scanner(params));
	}
	public Context get(Scanner s){
		if(s.hasNext()){
			Context c = ctxs.get(s.next());
			if(c != null){
				return c.get(s);
			}else{
				throw new UnrecognizedContext();
			}
		}
		return this;

	}
}
