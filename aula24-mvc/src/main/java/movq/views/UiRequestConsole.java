package movq.views;

import static java.lang.System.in;
import static java.lang.System.out;

import java.util.Scanner;

import movq.core.IMovieController;
import movq.core.IMovieUiRequest;

public class UiRequestConsole implements IMovieUiRequest{
	final IMovieController ctrl;
	
	public UiRequestConsole(IMovieController ctrl) {
		super();
		this.ctrl = ctrl;
	}
	@Override
	public void launch(){
		//
		// run command shell
		//
		Scanner cin = new Scanner(in);
		out.println("****** Commmand shell application *****");
		while(true){
			out.print("> ");
			out.flush();
			String inLine = cin.nextLine();
			ctrl.search(inLine);
		}		
	}
}
