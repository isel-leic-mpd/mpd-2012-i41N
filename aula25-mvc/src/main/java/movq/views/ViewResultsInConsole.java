package movq.views;
import javax.swing.event.ListDataEvent;

import movq.core.IListModelCollection;
import movq.core.IMovie;
import movq.core.IMovieQueryResult;

public class ViewResultsInConsole implements IMovieQueryResult{
	
	final IListModelCollection<IMovie> model;

	public ViewResultsInConsole(IListModelCollection<IMovie> model) {
		this.model = model;
		model.addListDataListener(this);
	}

	@Override
	public void showResult(Iterable<IMovie> movs) {
		System.out.println("------------------------------------");
		for (IMovie m : movs) {
			System.out.println(m.getName());
		}

	}

	@Override
	public void contentsChanged(ListDataEvent arg0) {
		showResult(model);
		
	}

	@Override
	public void intervalAdded(ListDataEvent arg0) {
		showResult(model);
	}

	@Override
	public void intervalRemoved(ListDataEvent arg0) {
		showResult(model);	
	}
}
