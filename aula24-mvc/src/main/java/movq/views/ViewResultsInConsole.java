package movq.views;
import movq.core.IMovie;
import movq.core.IMovieQueryResult;

public class ViewResultsInConsole implements IMovieQueryResult{

	@Override
	public void showResult(Iterable<IMovie> movs) {
		System.out.println("------------------------------------");
		for (IMovie m : movs) {
			System.out.println(m.getName());
		}

	}
}
