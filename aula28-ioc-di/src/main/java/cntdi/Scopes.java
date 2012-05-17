package cntdi;

public class Scopes {
	public static final IScope SINGLETON = new IScope() {
		@Override
		public <S> IProvider<S> scope(final IProvider<S> prov){
			return new IProvider<S>() {
				S cache;
				@Override
				public S get() {
					if(cache != null) return cache;
					cache = prov.get();
					return cache;
				}
			};
		}		
	};
}
