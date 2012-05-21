package movq.views;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.google.inject.Inject;

import movq.core.IMovie;
import movq.core.IMovieController;
import movq.core.IMovieFinder;
import movq.core.IMovieQueryResult;
import movq.core.IMovieUiRequest;
import movq.core.MovieQueryException;

public class UiRequestGui implements IMovieUiRequest{
	JFrame frm;

	@Inject
	public UiRequestGui(final IMovieController ctrl) {
		//
		// configure window
		// 			

		frm = new JFrame();
		frm.setLayout(new FlowLayout());
		frm.setTitle("Movie Finder");
		frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//
		// configure Gui components
		// 				
		JButton bt = new JButton("search");
		final JTextField txt = new JTextField(16);
		frm.add(new JLabel("key:"));
		frm.add(txt);
		frm.add(bt);
		//
		// configure ActionListener
		// 
		ActionListener a = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Iterable<IMovie> res;
				try {
					frm.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					ctrl.search(txt.getText());
				} catch (MovieQueryException e) {
					JOptionPane.showMessageDialog(frm, e.getMessage());
				}finally {
					  frm.setCursor(Cursor.getDefaultCursor());
				}

			}
		};
		bt.addActionListener(a);
		txt.addActionListener(a);
	}
	@Override
	public void launch() throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frm.pack();
				frm.setVisible(true);
			}
		});
	}	
}
