package fr.lokm.model.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import fr.lokm.model.utils.Country;
import javax.persistence.ManyToOne;

@Entity
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=70, nullable=false)
	private String firstname;
	@Column(length=70, nullable=false)
	private String lastname;
	@Column(length=70, nullable=false)
	private String native_country;
	
//	@ManyToMany(cascade=CascadeType.PERSIST)
//	@JoinTable(
//			name="authors_books",
//			joinColumns=@JoinColumn(name="author_id", referencedColumnName="id",foreignKey=@ForeignKey(name="fk_author")),
//			inverseJoinColumns=@JoinColumn(name="book_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_book")))
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="book_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_book"))
	private Book book;
	
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
//	public List<Book> getBooks() {
//		return books;
//	}
//	public void setBooks(List<Book> books) {
//		this.books = books;
//	}
//
//	public void addBooks(Book book) {
//		this.books.add(book);
//		book.addAuthor(this);
//	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
}
