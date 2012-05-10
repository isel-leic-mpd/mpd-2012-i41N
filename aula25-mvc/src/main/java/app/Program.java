package app;

import movq.core.IListModelCollection;
import movq.core.IMovie;
import movq.core.IMovieFinder;
import movq.core.IMovieQueryResult;
import movq.ctrl.MovieController;
import movq.finders.FinderJtmdbService;
import movq.model.ListModelCollection;
import movq.views.UiRequestConsole;
import movq.views.UiRequestGui;
import movq.views.ViewResultsInBillboard;
import movq.views.ViewResultsInConsole;
import movq.views.ViewResultsInJList;

public class Program {
	public static void main(String[] args) throws Exception {
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
	}
}
