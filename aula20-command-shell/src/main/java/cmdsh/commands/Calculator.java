package cmdsh.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import calc.IntExpression;
import calc.OperatorAdd;
import calc.OperatorFactory;
import calc.OperatorSub;
import calc.TerminalExpression;
import cmdsh.args.ArgsVar;
import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.parsers.ParamsParserOrdered;

public class Calculator extends AbstractCommand{
	final Map<Character, OperatorFactory> ops = new HashMap<Character, OperatorFactory>();

	public Calculator(Iterable<OperatorFactory> facs) {
		super("calc", new ParamsParserOrdered(), new IArgument[]{
			new ArgsVar()
		});
		for (OperatorFactory opFac : facs) {
			ops.put(opFac.getSymbol(), opFac);
		}
	}

	@Override
	protected void executeCommand() {
		Stack<IntExpression> stack = new Stack<IntExpression>();
		ArgsVar args = (ArgsVar) super.args().iterator().next();
		for (String a : args.getValue()) {
			OperatorFactory fac =  ops.get(a.charAt(0));
			if(fac != null){
				stack.push(fac.make(stack));
			}else{
				stack.push(new TerminalExpression(Integer.parseInt(a)));
			}
		}
		System.out.println("res = " + stack.pop().eval());
	}
}
