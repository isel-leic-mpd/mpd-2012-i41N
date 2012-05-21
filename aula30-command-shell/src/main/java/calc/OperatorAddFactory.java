package calc;

import java.util.Stack;

public class OperatorAddFactory implements OperatorFactory{

	@Override
	public Operator make(Stack<IntExpression> args) {
		return new OperatorAdd(args);
	}

	@Override
	public char getSymbol() {
		return '+';
	}

}
