package cmdsh.core;

import java.util.Scanner;

public interface IParamsParser {
	void parse(Scanner params, Iterable<IArgument> args);
}
