package model;

import java.util.Date;

public class Employee {
	final String title;
	final String firstName;
	final String lastName;
	final Date birthDate;
	
	public Employee(String title, String firstName, String lastName, Date birthDate) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Employee [title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + "]";
	}
}
