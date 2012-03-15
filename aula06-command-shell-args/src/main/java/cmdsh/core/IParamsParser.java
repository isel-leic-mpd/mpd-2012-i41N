package cmdsh.core;

public interface IParamsParser {
	void parse(String params, Iterable<IArgument> args);
}
