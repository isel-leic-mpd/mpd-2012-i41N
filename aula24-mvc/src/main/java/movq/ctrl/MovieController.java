package movq.ctrl;

import movq.core.IMovie;
import movq.core.IMovieController;
import movq.core.IMovieFinder;
import movq.core.IMovieQueryResult;
import movq.core.MovieQueryException;

public class MovieController implements IMovieController{
	
	final IMovieFinder finder;
	final IMovieQueryResult results;
	
	public MovieController(IMovieFinder finder, IMovieQueryResult results) {
		super();
		this.finder = finder;
		this.results = results;
	}

	@Override
	public void search(String title) throws MovieQueryException {
		Iterable<IMovie> res;
		try {
			res = finder.search(title);
			results.showResult(res);
		} catch (Exception e) {
			throw new MovieQueryException(e);
		}

	}

}
