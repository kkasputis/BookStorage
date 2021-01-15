package bs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bs.entity.AntiqueBook;
import bs.entity.Book;
import bs.entity.ScienceJournal;
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
			if (AntiqueBook.class == bookRepository.findFirstByBarcode(barcode).orElse(null).getClass()) {
				return antiqueBookService.calculatePrice(barcode);
			}
			if (ScienceJournal.class == bookRepository.findFirstByBarcode(barcode).orElse(null).getClass()) {
				return scienceJournalService.calculatePrice(barcode);
			}
			if (Book.class == bookRepository.findFirstByBarcode(barcode).orElse(null).getClass()) {
				return bookService.calculatePrice(barcode);
			}
			else {
				return "Could not find book type...";
			}
		}
		
		else {
			return "Cound not find book by barcode: " + barcode;
		}
		
	}
}
