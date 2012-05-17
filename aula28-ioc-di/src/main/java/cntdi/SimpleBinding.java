package cntdi;

import java.lang.reflect.Constructor;

public class SimpleBinding<T, S extends T> implements IBinding<T, S>{

	final Class<T> key;
	final IProvider<S> prov;
	
	public SimpleBinding(Class<T> key, IProvider<S> prov) {
		this.key = key;
		this.prov = prov;
	}

	@Override
	public S getInstance() {
		return prov.get();
	}
	@Override
	public Class<T> getKey() {
		return key;
	}


}
