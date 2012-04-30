package calc;

import java.util.Stack;

public class OperatorAdd extends Operator{

	public OperatorAdd(Stack<IntExpression> args) {
		super(args);
	}

	@Override
	public int eval() {
		return arg1.eval() + arg2.eval();
	}
}
