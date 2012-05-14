package cntdi;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class SimpleInjector implements IInjector{

	private Map<Class<?>, IBinding<?,?>> bindings = new HashMap<Class<?>, IBinding<?,?>>();
	private Map<Class<?>, IProvider<?>> providers = new HashMap<Class<?>, IProvider<?>>();
	
	public SimpleInjector(String propsFile){
		Properties p = new Properties();
		try{
			p.load(new FileInputStream(propsFile));

			for (Map.Entry<Object, Object> par : p.entrySet()) {
				Class<?> key = Class.forName(par.getKey().toString());
				Class<?> implClass = Class.forName(par.getValue().toString());
				IProvider prov = providers.get(implClass); 
				if(prov == null){
					prov = new ProviderSingleton(this, implClass);
					providers.put(implClass, prov);
				}
				bindings.put(key, new SimpleBinding(key, prov));
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public <T> T getInstance(Class<T> key){
		IBinding<?, ?> b = bindings.get(key); 
		return (T) b.getInstance();
	}

	@Override
	public <T> boolean contains(Class<T> key) {
		return bindings.containsKey(key);
	}
}
