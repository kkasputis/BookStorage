package bs.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bs.entity.AntiqueBook;
import bs.repository.AntiqueBookRepository;

@Service
public class AntiqueBookService implements BookService<AntiqueBook>{
	@Autowired
	AntiqueBookRepository bookRepository;

	public String save(AntiqueBook book) {
		if (book.getYear() <= 1900) {
			bookRepository.save(book);
			return "Saved.";

		} else {
			return "Book is not antique";
		}
	}

	public AntiqueBook findByBarcode(String barcode) {
		return bookRepository.findFirstByBarcode(barcode).orElse(null);
	}

	public AntiqueBook update(AntiqueBook newBook, String barcode) {
		AntiqueBook book = findByBarcode(barcode);
		if (book != null) {
			book.setAuthor(newBook.getAuthor());
			book.setName(newBook.getName());
			book.setPrice(newBook.getPrice());
			book.setQuantity(newBook.getQuantity());
			book.setYear(newBook.getYear());
			return bookRepository.save(book);
		}
		return bookRepository.save(newBook);

	}

	public String calculatePrice(String barcode) {
		AntiqueBook book = findByBarcode(barcode);
		if (book != null) {
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			BigDecimal yearMultiplier = new BigDecimal((currentYear - book.getYear())).divide(new BigDecimal(10), 6, RoundingMode.HALF_UP);
			BigDecimal totalPrice = book.getPrice().multiply(new BigDecimal(book.getQuantity())).multiply(yearMultiplier);
			return totalPrice.setScale(2, RoundingMode.CEILING).toString();
		} else {
			return "Could not find antique book with barcode: " + barcode;
		}
	}
}
