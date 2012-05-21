package app;

import com.google.inject.Guice;
import com.google.inject.Injector;

import movq.core.IMovieQueryResult;
import movq.core.IMovieUiRequest;
public class Program {
	public static void main(String[] args) throws Exception {
		Injector inj = Guice.createInjector(new CfgMovq());
		inj.getInstance(IMovieQueryResult.class);
		inj.getInstance(IMovieUiRequest.class).launch();
	}
}
