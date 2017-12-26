package fr.lokm.model.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=70, nullable=false, unique=true)
	private String pseudo;
	@Column(length=40, nullable=false)
	private String passwd;
	@Column(length=90, nullable=false)
	private String firstname;
	@Column(length=90, nullable=false)
	private String lastname;
	@Column(length=90, nullable=false, unique=true)
	private String email;
	@Column(length=20, nullable=false)
	private String role;
	
	public User() {}
	public User(String pseudo, String passwd, String firstname, String lastname, String email, String role) {
		this.pseudo = pseudo;
		this.passwd = passwd;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
	}
	public User(int id, String pseudo, String passwd, String firstname, String lastname, String email, String role) {
		this(pseudo, passwd, firstname, lastname, email, role);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
