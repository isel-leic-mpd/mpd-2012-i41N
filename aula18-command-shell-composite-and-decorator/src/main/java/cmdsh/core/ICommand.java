package cmdsh.core;

import java.util.Scanner;

public interface ICommand {
	String getName();
	void performCommand(Scanner params);
	Iterable<IArgument> args();
}
