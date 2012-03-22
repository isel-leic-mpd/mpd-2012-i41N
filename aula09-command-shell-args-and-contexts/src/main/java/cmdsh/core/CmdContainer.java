package cmdsh.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import collect.IterUtils;
import collect.Predicate;

public class CmdContainer {
	/* ==========================================================
	 * *************************** FIELDS ***********************
	 * ==========================================================
	 */
	/**
	 * @uml.property   name="cmds"
	 * @uml.associationEnd   multiplicity="(0 -1)" aggregation="shared" qualifier="key:java.lang.String cmdsh.core.ICommand"
	 */
	private final List<ICommand> cmds;

	/* ==========================================================
	 * ***************************  CTOR  ***********************
	 * ==========================================================
	 */
	public CmdContainer(ICommand...inCmds){
		cmds = new LinkedList<ICommand>();
		for (ICommand c : inCmds) {
			cmds.add(c);
		}
	}
	/* ==========================================================
	 * ************************** METHODS  **********************
	 * ==========================================================
	 */	
	public ICommand getCommand(final String args){
		ICommand c = IterUtils.find(cmds, new Predicate<ICommand>() {public boolean invoke(ICommand item) {
				return args.indexOf(item.getName()) == 0;
			}
		});
		if(c == null) throw new UnrecognizedCommand();
		return c;
	} 
}






