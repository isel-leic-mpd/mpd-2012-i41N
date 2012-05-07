package movq.views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import movq.core.IMovie;
import movq.core.IMovieController;
import movq.core.IMovieFinder;
import movq.core.IMovieQueryResult;
import movq.core.IMovieUiRequest;
import movq.core.MovieQueryException;

public class UiRequestGui implements IMovieUiRequest{
	final IMovieController ctrl;
	
	public UiRequestGui(IMovieController ctrl) {
		super();
		this.ctrl = ctrl;
	}
	@Override
	public void launch() throws Exception {
		final JFrame frm = new JFrame();
		frm.setLayout(new FlowLayout());
		JButton bt = new JButton("search");
		final JTextField txt = new JTextField(16);
		frm.add(new JLabel("key:"));
		frm.add(txt);
		frm.add(bt);
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Iterable<IMovie> res;
				try {
					ctrl.search(txt.getText());
				} catch (MovieQueryException e) {
					JOptionPane.showMessageDialog(frm, e.getMessage());
				}

			}
		});
		frm.pack();
		frm.setVisible(true);
	}	
}
