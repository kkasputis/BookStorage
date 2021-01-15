package bs.entity;


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
	
	
}
