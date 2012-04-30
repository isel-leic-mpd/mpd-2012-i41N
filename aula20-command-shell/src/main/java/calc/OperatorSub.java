package calc;

import java.util.Stack;

public class OperatorSub extends Operator{

	public OperatorSub(Stack<IntExpression> args) {
		super(args);
	}

	@Override
	public int eval() {
		return arg1.eval() - arg2.eval();
	}
}
