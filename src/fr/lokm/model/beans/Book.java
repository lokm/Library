package fr.lokm.model.beans;

import java.util.LinkedHashSet;
import java.util.Set;

public class Book {
	private int id;
	private String title;
	private float price;
	private boolean availability;
	private String overview;
	private Set<Author> authors;
	
	public Book() {
		authors = new LinkedHashSet<Author>();
	}
	public Book(String title, String overview, int price, boolean availability, Set<Author> authors) {
		this();
		this.title = title;
		this.overview = overview;
		this.price = price;
		this.availability = availability;
		this.authors = authors;
	}
	public Book(int id, String title, String overview, int price, boolean availability, Set<Author> authors) {
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
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(Author author) {
		this.authors.add(author);
	}
}
