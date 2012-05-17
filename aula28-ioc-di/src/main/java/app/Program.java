package app;

import movq.core.IMovieQueryResult;
import movq.core.IMovieUiRequest;
import cntdi.IInjector;
import cntdi.SimpleInjector;

public class Program {
	final static String cfgFile = "D:\\MyFolder\\ISEL\\Pg4 mpd - 2011-2012 2º sem\\i41N-git-repo\\MovieQuery.properties"; 
	
	public static void main(String[] args) throws Exception {
		/*
		IMovieFinder finder = new FinderJtmdbService();
		
		IListModelCollection<IMovie> model = new ListModelCollection<IMovie>();

		// IMovieQueryResult results = new ViewResultsInConsole();
		IMovieQueryResult results = new ViewResultsInBillboard(model);
		// IMovieQueryResult results = new ViewResultsInJList(model);

		
		//new UiRequestConsole(
		new UiRequestGui(
				new MovieController(
						finder, model)
		).launch();
		*/
		
		//IInjector inj = new SimpleInjector(cfgFile);
		IInjector inj = new SimpleInjector(new CfgMovq());
		inj.getInstance(IMovieQueryResult.class);
		inj.getInstance(IMovieUiRequest.class).launch();
	}
}
