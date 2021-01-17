package bs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bs.repository.BookRepository;

@Service
public class PriceService {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	BookServiceImpl bookService;
	@Autowired
	AntiqueBookService antiqueBookService;
	@Autowired
	ScienceJournalService scienceJournalService;

	public String calculate(String barcode) {
		if (bookRepository.findFirstByBarcode(barcode).orElse(null) != null) {
			String className = bookRepository.findFirstByBarcode(barcode).get().getClass().getName();
			switch(className) {
			  case "bs.entity.AntiqueBook":
				  return antiqueBookService.calculatePrice(barcode);
			    
			  case "bs.entity.ScienceJournal":
				  return scienceJournalService.calculatePrice(barcode);
			  case "bs.entity.Book":
				  return bookService.calculatePrice(barcode);
			  default:
			    return "Could not find book type...";
			}

		}
		
		else {
			return "Cound not find book by barcode: " + barcode;
		}
		
	}
}
