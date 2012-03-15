package cmdsh.core;

public interface IArgument<T>{
	int parse(String[] args, int from);
	T getValue();
}
