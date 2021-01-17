package bs.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bs.entity.ScienceJournal;
import bs.repository.BookRepository;
import bs.repository.ScienceJournalRepository;

@Service
public class ScienceJournalService implements BookService<ScienceJournal>{
	@Autowired
	ScienceJournalRepository scienceJournalRepository;
	@Autowired
	BookRepository bookRepository;

	public String save(ScienceJournal journal) {
		if (bookRepository.existsByBarcode(journal.getBarcode())) {
			return "Book with this barcode already exist.";
		}
		if ((journal.getScienceIndex() > 0) && (journal.getScienceIndex() < 11)) {
			try {
				scienceJournalRepository.save(journal);
				return "Saved";
			} catch (Exception e) {
				return "Could not save journal because:" + e;
			}

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


	public List<ScienceJournal> findAll() {
		return scienceJournalRepository.findAll();
	}
}
