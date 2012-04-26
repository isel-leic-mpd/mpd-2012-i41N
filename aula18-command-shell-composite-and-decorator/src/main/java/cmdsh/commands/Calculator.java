package cmdsh.commands;

import java.util.Stack;

import calc.IntExpression;
import calc.OperatorAdd;
import calc.OperatorSub;
import calc.TerminalExpression;
import cmdsh.args.ArgsVar;
import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.parsers.ParamsParserOrdered;

public class Calculator extends AbstractCommand{

	public Calculator() {
		super("calc", new ParamsParserOrdered(), new IArgument[]{
			new ArgsVar()
		});
	}

	@Override
	protected void executeCommand() {
		Stack<IntExpression> stack = new Stack<IntExpression>();
		ArgsVar args = (ArgsVar) super.args().iterator().next();
		for (String a : args.getValue()) {
			switch (a.charAt(0)) {
				case '+':
					stack.push(new OperatorAdd(stack));
					break;
				case '-':
					stack.push(new OperatorSub(stack));
					break;
				default:
					stack.push(new TerminalExpression(Integer.parseInt(a)));
			}
		}
		System.out.println("res = " + stack.pop().eval());
	}
}
