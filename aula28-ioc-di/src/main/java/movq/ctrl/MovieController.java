package movq.ctrl;

import java.util.Collection;

import movq.core.IMovie;
import movq.core.IMovieController;
import movq.core.IMovieFinder;
import movq.core.IMovieQueryResult;
import movq.core.MovieQueryException;

/**
 * @author  mcarvalho
 */
public class MovieController implements IMovieController{
	
	/**
	 * @uml.property  name="finder"
	 * @uml.associationEnd  
	 */
	final IMovieFinder finder;
	/**
	 * @uml.property  name="model"
	 * @uml.associationEnd  
	 */
	final Collection<IMovie> model;
	
	public MovieController(IMovieFinder finder, Collection<IMovie> model) {
		super();
		this.finder = finder;
		this.model = model;
	}

	@Override
	public void search(String title) throws MovieQueryException {
		Iterable<IMovie> res;
		try {
			res = finder.search(title);
			model.clear();
			for (IMovie m : res) {
				model.add(m);
			}
		} catch (Exception e) {
			throw new MovieQueryException(e);
		}

	}

}
