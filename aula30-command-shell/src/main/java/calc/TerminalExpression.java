package calc;

public class TerminalExpression implements IntExpression{
	private final int val;
	
	public TerminalExpression(int val) {
		super();
		this.val = val;
	}

	public int eval(){
		return val;
	}
}
