package cntdi;

public interface IScope{
	<S> IProvider<S> scope(IProvider<S> prov);
	
}
