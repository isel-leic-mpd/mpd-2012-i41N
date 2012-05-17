package cntdi;

public interface IBinder {
	<T> IBindingBuilder<T> bind(Class<T> key);
}
