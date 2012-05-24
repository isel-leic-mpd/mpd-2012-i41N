package app;

import java.sql.SQLException;
import java.util.Scanner;

import orm.DbUtil;

import com.google.inject.Guice;
import com.google.inject.Injector;

import static java.lang.System.in;
import static java.lang.System.out;

public class Program {

	public static void main(String[] args) throws SQLException {		
		Injector inj = Guice.createInjector(new CfgModule());
		DbUtil db = inj.getInstance(DbUtil.class);
		
		//
		// run command shell
		//
		Scanner cin = new Scanner(in);
		out.println("****** Commmand shell application *****");
		while(true){
			out.print("> Insert the employee id: ");
			out.flush();
			String inLine = cin.nextLine();
			int id = Integer.parseInt(inLine);
			out.println(db.loadEmployeeById(id));
		}
	}
}
