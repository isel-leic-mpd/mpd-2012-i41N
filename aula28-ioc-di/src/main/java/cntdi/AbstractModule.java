package cntdi;

public abstract class AbstractModule implements IModule{
	IBinder binder;
	
	@Override
	public void configure(IBinder binder) {
		this.binder = binder;
		this.configure();
	}
	protected abstract void configure();
	
	public <T> IBindingBuilder<T> bind(Class<T> key) {
		return binder.bind(key);
	}
}
