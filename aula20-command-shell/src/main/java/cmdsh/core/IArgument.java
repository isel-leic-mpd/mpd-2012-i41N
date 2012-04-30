package cmdsh.core;

import java.util.Scanner;

public interface IArgument<T>{
	int parse(String [] args, int from);
	T getValue();
	String getName();
}
