package guiutil;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Billboard{
	public static void main(String []args){
		Billboard.launch();
	}
	
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final JTextArea board;
	
	public Billboard(){
		board = new JTextArea();
		board.setPreferredSize(new Dimension(300,300));
	}
	
	public static Billboard launch(){
		final Billboard bb  = new Billboard(); 
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JDialog dialog = new JDialog();
				dialog.setLayout(new BorderLayout());// Strategy pattern
				dialog.add(
						new JScrollPane(bb.board), // Decorator pattern -> component wrapped inside a scroller 
						BorderLayout.CENTER); // Strategy pattern
				dialog.setModal(true);
				dialog.setTitle("Billboard");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.pack();
				dialog.setVisible(true);
			}
		});
		return bb;
	}
	public void addText(String text){
		board.append(text + NEWLINE);
	}
}
