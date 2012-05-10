package movq.finders;

import java.util.Arrays;
import java.util.List;

import movq.core.IMovie;
import movq.core.IMovieFinder;

import com.jtmdb.GeneralSettings;
import com.jtmdb.Movie;

public class FinderJtmdbService implements IMovieFinder{
	public static void main(String [] args) throws Exception{		
		for (IMovie m : new FinderJtmdbService().search("black")) {
			System.out.println(m.getName());
		}
	}
	
	public FinderJtmdbService() {
		GeneralSettings.setApiKey("d9a8d6b59432965c394b48f3b67c056f");
	}

	public Iterable<IMovie> search(String title) throws Exception {
		List<Movie> res = Movie.search(title);
		return Arrays.asList(res.toArray(new IMovie[res.size()]));
	}

}
