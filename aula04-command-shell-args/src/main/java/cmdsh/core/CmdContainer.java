package cmdsh.core;

import java.util.HashMap;
import java.util.Map;

public class CmdContainer {
	/* ==========================================================
	 * *************************** FIELDS ***********************
	 * ==========================================================
	 */
	/**
	 * @uml.property   name="cmds"
	 * @uml.associationEnd   multiplicity="(0 -1)" aggregation="shared" qualifier="key:java.lang.String cmdsh.core.ICommand"
	 */
	private final Map<String, ICommand> cmds;

	/* ==========================================================
	 * ***************************  CTOR  ***********************
	 * ==========================================================
	 */
	public CmdContainer(ICommand...inCmds){
		cmds = new HashMap<String, ICommand>();
		for (ICommand c : inCmds) {
			cmds.put(c.getName(), c);
		}
	}
	/* ==========================================================
	 * ************************** METHODS  **********************
	 * ==========================================================
	 */	
	public ICommand getCommand(String args){
		ICommand c = cmds.get(args);
		String argsAux = args;
		while(c == null && argsAux.length() > 0){
			int idx = 0;
			argsAux = argsAux.substring(0, (idx = argsAux.lastIndexOf(" ")) < 0? 0: idx);
			c = cmds.get(argsAux);
		}
		if(c == null) 
			throw new UnrecognizedCommand();
		return c;
	} 
}