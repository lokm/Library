package fr.lokm.model.beans;

//import fr.lokm.model.utils.Country;

public class Author {
	private int id;
	private String firstname;
	private String lastname;
	private String native_country;
	
	public Author() {}
	public Author(String firstname, String lastname, String native_country) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.native_country = native_country;
	}
	public Author(int id,String firstname, String lastname, String native_country) {
		this(firstname, lastname, native_country);
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getNative_country() {
		return native_country;
	}
	public void setNative_country(String native_country) {
		this.native_country = native_country;
	}

	
}
