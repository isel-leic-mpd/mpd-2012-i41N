package cntdi;

public interface IBinding<T, S extends T> {
	Class<T> getKey();
	S getInstance();
}
