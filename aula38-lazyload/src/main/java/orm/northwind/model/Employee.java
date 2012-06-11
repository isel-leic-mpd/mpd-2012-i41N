package orm.northwind.model;

import java.util.Date;

import orm.Entity;
import orm.ValueHolder;

public class Employee implements Entity<Integer>{
	int id;
	String title;
	String firstName;
	String lastName;
	Iterable<Employee> employees;
	ValueHolder<Employee> reportsTo;
	
	Date birthDate;
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Iterable<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Iterable<Employee> employees) {
		this.employees = employees;
	}

	public Employee getReportsTo() {
		return reportsTo.get();
	}

	public void setReportsTo(final Employee reportsTo) {
		this.reportsTo = new ValueHolder<Employee>() {
			public Employee get() {return reportsTo;}
		};
	}

	public Employee(){}
	public Employee(int id, String title, String firstName, String lastName, Date birthDate, Iterable<Employee> employees, ValueHolder<Employee> reportsTo) {
		super();
		this.id = id;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.employees = employees;
		this.reportsTo = reportsTo;
	}

	public Employee(String title, String firstName, String lastName, Date birthDate) {
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "Employee [title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + "]";
	}

	public String getTitle() {
		return title;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
