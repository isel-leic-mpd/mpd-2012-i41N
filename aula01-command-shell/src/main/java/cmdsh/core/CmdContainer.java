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
		return cmds.get(args);
	} 
}