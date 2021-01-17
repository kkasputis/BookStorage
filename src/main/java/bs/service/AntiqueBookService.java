package bs.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bs.entity.AntiqueBook;
import bs.repository.AntiqueBookRepository;
import bs.repository.BookRepository;

@Service
public class AntiqueBookService implements BookService<AntiqueBook> {
	@Autowired
	AntiqueBookRepository antiqueBookRepository;
	@Autowired
	BookRepository bookRepository;

	public String save(AntiqueBook book) {
		if (bookRepository.existsByBarcode(book.getBarcode())) {
			return "Book with this barcode already exist.";
		}
		if (book.getYear() <= 1900) {
			try {
				antiqueBookRepository.save(book);
				return "Saved";
			} catch (Exception e) {
				return "Could not save book because:" + e;
			}
		} else {
			return "Book is not antique";
		}
	}

	public AntiqueBook findByBarcode(String barcode) {
		return antiqueBookRepository.findFirstByBarcode(barcode).orElse(null);
	}

	public AntiqueBook update(AntiqueBook newBook, String barcode) {
		AntiqueBook book = findByBarcode(barcode);
		if (book != null) {
			book.setAuthor(newBook.getAuthor());
			book.setName(newBook.getName());
			book.setPrice(newBook.getPrice());
			book.setQuantity(newBook.getQuantity());
			book.setYear(newBook.getYear());
			return antiqueBookRepository.save(book);
		}
		return antiqueBookRepository.save(newBook);

	}
	public List<AntiqueBook> findAll() {
		return antiqueBookRepository.findAll();
	}
	public String calculatePrice(String barcode) {
		AntiqueBook book = findByBarcode(barcode);

		if (book != null) {
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			BigDecimal yearMultiplier = new BigDecimal((currentYear - book.getYear())).divide(new BigDecimal(10), 6,
					RoundingMode.HALF_UP);
			BigDecimal totalPrice = book.getPrice().multiply(new BigDecimal(book.getQuantity()))
					.multiply(yearMultiplier);
			return totalPrice.setScale(2, RoundingMode.CEILING).toString();
		} else {
			return "Could not find antique book with barcode: " + barcode;
		}
	}
}
