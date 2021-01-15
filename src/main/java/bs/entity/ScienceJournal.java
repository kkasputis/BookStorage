package bs.entity;


import java.math.BigDecimal;

import javax.persistence.Entity;


@Entity
public class ScienceJournal extends Book {

	private int scienceIndex;

	public int getScienceIndex() {
		return scienceIndex;
	}
	public void setScienceIndex(int scienceIndex) {
		this.scienceIndex = scienceIndex;
	}
	public ScienceJournal() {
		super();
	}
	public ScienceJournal(String name, String author, String barcode, int quantity, BigDecimal price) {
		super(name, author, barcode, quantity, price);
	}
	
	
}
