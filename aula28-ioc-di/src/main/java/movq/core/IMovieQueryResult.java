package movq.core;

import javax.swing.event.ListDataListener;

public interface IMovieQueryResult extends ListDataListener{
	void showResult(Iterable<IMovie> movs);
}
