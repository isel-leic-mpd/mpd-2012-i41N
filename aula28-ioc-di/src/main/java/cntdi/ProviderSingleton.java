package cntdi;

public class ProviderSingleton<S> implements IProvider<S>{

	final ProviderMultiple<S> prov;
	S cache;
	
	public ProviderSingleton(IInjector inj, Class<S> impl) {
		prov = new ProviderMultiple<S>(inj, impl);
	}

	@Override
	public S get() {
		if(cache != null) return cache;
		cache = prov.get();
		return cache;
	}

}
