package bs.entity;


import java.math.BigDecimal;

import javax.persistence.Entity;



@Entity
public class AntiqueBook extends Book{

	long id;
	long bookId;
	private int year;

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public AntiqueBook() {
		super();
	}
	public AntiqueBook(String name, String author, String barcode, int quantity, BigDecimal price) {
		super(name, author, barcode, quantity, price);

	}
	
}
