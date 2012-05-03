package app;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Program {

	public static void main(String [] args){
		JFrame frm = new JFrame();
		frm.setLayout(new FlowLayout());
		final JButton bt1 = new JButton("Click");
		final JButton bt2 = new JButton("<= Forward");
		frm.add(bt1);
		frm.add(bt2);
		bt1.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(bt1, "bt1 clicked!");
			}
		});
		bt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(bt1, "Hello!");
			}
		});
		bt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(bt2, "Hello2!");
				bt1.dispatchEvent(arg0);
			}
		});
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frm.pack();
		frm.setVisible(true);
	}
}
