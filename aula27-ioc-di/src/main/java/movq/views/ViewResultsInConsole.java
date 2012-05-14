package movq.views;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;

import movq.core.IListModelCollection;
import movq.core.IMovie;
import movq.core.IMovieQueryResult;

public class ViewResultsInConsole implements IMovieQueryResult{
	
	final ListModel<IMovie> model;

	public ViewResultsInConsole(ListModel<IMovie> model) {
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
	public void showResult(final ListModel<IMovie> movs) {
		showResult(new Iterable<IMovie>() {public Iterator<IMovie> iterator() {
				return new Iterator<IMovie>() {
					int idx = 0;
					@Override
					public boolean hasNext() {
						return idx < movs.getSize();
					}

					@Override
					public IMovie next() {
						return movs.getElementAt(idx++);
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		});
	}

}
