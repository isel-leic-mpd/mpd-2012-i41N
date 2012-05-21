package movq.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListDataEvent;

import com.google.inject.Inject;

import movq.core.IListModelCollection;
import movq.core.IMovie;
import movq.core.IMovieQueryResult;

public class ViewResultsInBillboard implements IMovieQueryResult{
	private static final String NEWLINE = System.getProperty("line.separator");

	private final JTextArea board;
	private final ListModel<IMovie> model;

	@Inject
	public ViewResultsInBillboard(ListModel<IMovie> model){
		this(model, Color.WHITE, Color.black);
	}

	public ViewResultsInBillboard(ListModel<IMovie> model, Color back, Color fg){
		model.addListDataListener(this);
		this.model = model;
		board = new JTextArea();
		board.setPreferredSize(new Dimension(300,300));
		board.setBackground(back);
		board.setForeground(fg);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JDialog dialog = new JDialog();
				dialog.setLayout(new BorderLayout());// Strategy pattern
				dialog.add(
						new JScrollPane(board), // Decorator pattern -> component wrapped inside a scroller 
						BorderLayout.CENTER); // Strategy pattern
				dialog.setModal(false);
				dialog.setTitle("Billboard");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}
	@Override
	public void showResult(Iterable<IMovie> movs) {
		board.setText("");
		for (IMovie m : movs){
			board.append(m.getName());
			board.append(NEWLINE);
		}
		board.invalidate();
		board.repaint();
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
