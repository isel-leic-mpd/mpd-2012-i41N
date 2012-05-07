package app;

import movq.core.IMovieFinder;
import movq.core.IMovieQueryResult;
import movq.ctrl.MovieController;
import movq.finders.FinderJtmdbService;
import movq.views.UiRequestConsole;
import movq.views.UiRequestGui;
import movq.views.ViewResultsInConsole;

public class Program {
	public static void main(String[] args) throws Exception {
		IMovieFinder finder = new FinderJtmdbService();
		IMovieQueryResult results = new ViewResultsInConsole();
		// new UiRequestConsole(finder, results).launch();
		new UiRequestGui(
				new MovieController(
						finder, results)
		).launch();
	}
}
