package movq.model;

import java.util.Collection;
import java.util.Iterator;

import javax.swing.DefaultListModel;

import movq.core.IListModelCollection;

public class ListModelCollection<E> extends DefaultListModel<E> implements IListModelCollection<E>{

	@Override
	public boolean add(E arg0) {
		this.addElement(arg0);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		for (E e : arg0) {
			this.add(e);	
		}
		return true;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			int idx = 0;
			@Override
			public boolean hasNext() {
				return idx < size();
			}
			@Override
			public E next() {
				return getElementAt(idx++);
			}
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public boolean remove(Object arg0) {
		return this.removeElement(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		throw new UnsupportedOperationException();
	}
}
