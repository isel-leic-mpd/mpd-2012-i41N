package app;

import java.util.Collection;

import javax.swing.ListModel;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;

import movq.core.IMovie;
import movq.core.IMovieController;
import movq.core.IMovieFinder;
import movq.core.IMovieQueryResult;
import movq.core.IMovieUiRequest;
import movq.ctrl.MovieController;
import movq.finders.FinderJtmdbService;
import movq.model.ListModelCollection;
import movq.views.UiRequestGui;
import movq.views.ViewResultsInBillboard;

public class CfgMovq extends AbstractModule{
	
	public void configure(){
		bind(IMovieFinder.class).to(FinderJtmdbService.class);
		
		bind(IMovieQueryResult.class).to(ViewResultsInBillboard.class);
		// bind(IMovieQueryResult.class).to(ViewResultsInConsole.class);

		ListModelCollection<IMovie> model = new ListModelCollection<IMovie>();
		
		bind(new TypeLiteral<ListModel<IMovie>>(){}).toInstance(model);
		
		bind(IMovieUiRequest.class).to(UiRequestGui.class);
		// bind(IMovieUiRequest.class).to(UiRequestConsole.class);
		
		bind(IMovieController.class).to(MovieController.class);

		bind(new TypeLiteral<Collection<IMovie>>(){}).toInstance(model);

	}
}
