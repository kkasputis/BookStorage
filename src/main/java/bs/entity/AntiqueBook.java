package bs.entity;


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
	
}
