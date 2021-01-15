package bs.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bs.entity.ScienceJournal;
import bs.repository.ScienceJournalRepository;

@Service
public class ScienceJournalService implements BookService<ScienceJournal>{
	@Autowired
	ScienceJournalRepository scienceJournalRepository;

	public String save(ScienceJournal journal) {
		if ((journal.getScienceIndex() > 0) && (journal.getScienceIndex() < 11)) {
			scienceJournalRepository.save(journal);
			return "Saved";
		} else {
			return "Science index out of boundries";
		}
	}

	public ScienceJournal findByBarcode(String barcode) {
		return scienceJournalRepository.findFirstByBarcode(barcode).orElse(null);
	}

	public ScienceJournal update(ScienceJournal newJournal, String barcode) {
		ScienceJournal journal = findByBarcode(barcode);
		if (journal != null) {
			journal.setAuthor(newJournal.getAuthor());
			journal.setName(newJournal.getName());
			journal.setPrice(newJournal.getPrice());
			journal.setQuantity(newJournal.getQuantity());
			journal.setScienceIndex(newJournal.getScienceIndex());
			return scienceJournalRepository.save(journal);
		}
		return scienceJournalRepository.save(newJournal);

	}

	public String calculatePrice(String barcode) {
		ScienceJournal journal = findByBarcode(barcode);
		if (journal != null) {
			BigDecimal totalPrice = journal.getPrice().multiply(new BigDecimal(journal.getQuantity()))
					.multiply(new BigDecimal(journal.getScienceIndex()));
			return totalPrice.toString();
		} else {
			return "Could not find journal with barcode: " + barcode;
		}
	}
}
