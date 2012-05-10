package movq.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListDataEvent;

import movq.core.IListModelCollection;
import movq.core.IMovie;
import movq.core.IMovieQueryResult;

public class ViewResultsInJList implements IMovieQueryResult{
	private static final String NEWLINE = System.getProperty("line.separator");

	private final JList<IMovie> board;

	public ViewResultsInJList(IListModelCollection<IMovie> model){
		this(model, Color.WHITE, Color.black);
	}

	public ViewResultsInJList(IListModelCollection<IMovie> model, Color back, Color fg){
		board = new JList<IMovie>();
		
		board.setModel(model); // internamente => model.addListDatListener(this)
		
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
	}

	@Override
	public void contentsChanged(ListDataEvent arg0) {
	}

	@Override
	public void intervalAdded(ListDataEvent arg0) {
	}
	@Override
	public void intervalRemoved(ListDataEvent arg0) {
	}
}
