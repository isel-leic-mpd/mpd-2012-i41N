package movq.core;

public interface IMovieFinder {
	Iterable<IMovie> search(String title) throws Exception;
}
