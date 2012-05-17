package cntdi;

public interface IBindingBuilder<T>{
	<S extends T> IScopedBindingBuilder to(Class<S> impl);
}
