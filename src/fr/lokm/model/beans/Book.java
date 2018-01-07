package fr.lokm.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
@Entity
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=70, nullable=false)
	private String title;
	@Column(precision=5, scale=2, nullable=false)
	private float price;
	
	private boolean availability;
	@Column(columnDefinition="TEXT")
	private String overview;
	
//	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="book",  fetch=FetchType.EAGER)
//	@ManyToMany
//	@JoinTable(
//		name="Authors_Books",
//		joinColumns=@JoinColumn(name="author_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_authors")),
//		inverseJoinColumns=@JoinColumn(name="book_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_books"))
//)
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
			name="Authors_Books",
			joinColumns=@JoinColumn(name="author_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_authors")),
			inverseJoinColumns=@JoinColumn(name="book_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_books"))
				)
	private List<Author> authors = new ArrayList<Author>();
	
	public Book() {}
	public Book(String title, String overview, float price, boolean availability, List<Author> authors) {
		this();
		this.title = title;
		this.overview = overview;
		this.price = price;
		this.availability = availability;
		this.authors = authors;
	}
	public Book(int id, String title, String overview, float price, boolean availability, List<Author> authors) {
		this(title, overview, price, availability, authors);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(Author author) {
		this.authors.add(author);
		author.addBooks(this);
	}
}
