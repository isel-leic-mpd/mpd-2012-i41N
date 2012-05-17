package cntdi;

public interface IInjector {
	<T> T getInstance(Class<T> key);
	<T> boolean contains(Class<T> key);
}
