package cntdi;

import java.lang.reflect.Constructor;

public class ProviderMultiple<S> implements IProvider<S>{
	
	final Class<S> impl;
	final IInjector inj;
	Constructor<S> ctor;
	Class [] paramsClasses;
	
	

	public ProviderMultiple(IInjector inj, Class<S> impl) {
		this.impl= impl;
		this.inj = inj;
	}

	@Override
	public S get() {
		if(ctor != null){
			Object [] params = new Object[paramsClasses.length];
			for (int i = 0; i < params.length; i++) {
				params[i] = inj.getInstance(paramsClasses[i]);
			}
			try {
				return (S) ctor.newInstance(params);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		Constructor [] ctors = impl.getConstructors();
		for (Constructor c : ctors) {
			paramsClasses = c.getParameterTypes();
			int count = 0;
			for (Class<?> p : paramsClasses) {
				if(!inj.contains(p)){
					break;
				}
				count++;
			}
			if(count == paramsClasses.length){
				ctor = c;
				break;
			}
		}
		if(ctor == null)
			throw new MissingInjectionsException("There are missing injections for class: " + impl.getName());

		Object [] params = new Object[paramsClasses.length];
		for (int i = 0; i < params.length; i++) {
			params[i] = inj.getInstance(paramsClasses[i]);
		}
		try {
			return (S) ctor.newInstance(params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
