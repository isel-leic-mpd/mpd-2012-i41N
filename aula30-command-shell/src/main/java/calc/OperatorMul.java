package calc;

import java.util.Stack;

public class OperatorMul extends Operator{

	public OperatorMul(Stack<IntExpression> args) {
		super(args);
	}

	@Override
	public int eval() {
		return arg1.eval() * arg2.eval();
	}
}
