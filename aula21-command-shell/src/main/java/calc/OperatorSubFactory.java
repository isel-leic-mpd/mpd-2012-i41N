package calc;

import java.util.Stack;

public class OperatorSubFactory implements OperatorFactory{

	@Override
	public Operator make(Stack<IntExpression> args) {
		return new OperatorSub(args);
	}

	@Override
	public char getSymbol() {
		return '-';
	}

}
