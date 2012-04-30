package calc;

import java.util.Stack;

public class OperatorMulFactory implements OperatorFactory{

	@Override
	public Operator make(Stack<IntExpression> args) {
		return new OperatorMul(args);
	}

	@Override
	public char getSymbol() {
		return '*';
	}

}
