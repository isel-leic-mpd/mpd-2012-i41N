package cntdi;

public class MissingInjectionsException extends RuntimeException {

	private static final long serialVersionUID = -4048473537052404148L;

	public MissingInjectionsException(String msg) {
		super(msg);
	}

}
