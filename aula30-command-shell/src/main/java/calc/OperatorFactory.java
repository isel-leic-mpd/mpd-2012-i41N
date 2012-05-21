package calc;

import java.util.Stack;

public interface OperatorFactory {
	Operator make(Stack<IntExpression> args);
	char getSymbol();
}
