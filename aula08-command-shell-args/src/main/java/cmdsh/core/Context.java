package cmdsh.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Context {
	private final Map<String, Context> ctxs;
	private ICommand cmd;

	public Context() {
		this.ctxs = new HashMap<String, Context>();
	}

	public void add(ICommand c){
		add(c, new Scanner(c.getName()));
	}

	private void add(ICommand c, Scanner s){
		if(s.hasNext()){
			String ctxName = s.next();
			Context ctx = ctxs.get(ctxName);
			if(ctx == null){
				ctx = new Context();
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
			}
		}
		return this;

	}
}
