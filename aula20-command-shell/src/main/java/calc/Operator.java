package calc;

import java.util.Stack;

public abstract class Operator implements IntExpression{
	protected final IntExpression arg1, arg2;

	public Operator(Stack<IntExpression> args) {
		this.arg1 = args.pop();
		this.arg2 = args.pop();
	}
}
