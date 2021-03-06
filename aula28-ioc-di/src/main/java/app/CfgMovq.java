package app;

import java.util.Collection;

import javax.swing.ListModel;

import movq.core.IMovieController;
import movq.core.IMovieFinder;
import movq.core.IMovieQueryResult;
import movq.core.IMovieUiRequest;
import movq.ctrl.MovieController;
import movq.finders.FinderJtmdbService;
import movq.model.ListModelCollection;
import movq.views.UiRequestConsole;
import movq.views.UiRequestGui;
import movq.views.ViewResultsInBillboard;
import movq.views.ViewResultsInConsole;
import cntdi.AbstractModule;
import cntdi.Scopes;

public class CfgMovq extends AbstractModule{
	
	public void configure(){
		bind(IMovieFinder.class).to(FinderJtmdbService.class);
		
		bind(IMovieQueryResult.class).to(ViewResultsInBillboard.class);
		// bind(IMovieQueryResult.class).to(ViewResultsInConsole.class);

		bind(ListModel.class).to(ListModelCollection.class).in(Scopes.SINGLETON);
		
		bind(IMovieUiRequest.class).to(UiRequestGui.class);
		// bind(IMovieUiRequest.class).to(UiRequestConsole.class);
		
		bind(IMovieController.class).to(MovieController.class);

		bind(Collection.class).to(ListModelCollection.class).in(Scopes.SINGLETON);

	}
}
