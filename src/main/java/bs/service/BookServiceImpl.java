package bs.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bs.entity.Book;
import bs.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService<Book> {
	@Autowired
	BookRepository bookRepository;

	
	public String save(Book book) {
		try {
			bookRepository.save(book); 
			return "Saved"; }
		catch(Exception e) {
			return "Could not save book because:" + e;
		}
	}

	public Book findByBarcode(String barcode) {
		return bookRepository.findFirstByBarcode(barcode).orElse(null);
	}


	public Book update(Book newBook, String barcode) {
		Book book = findByBarcode(barcode);
		if (book != null) {
			book.setAuthor(newBook.getAuthor());
			book.setName(newBook.getName());
			book.setPrice(newBook.getPrice());
			book.setQuantity(newBook.getQuantity());
			return bookRepository.save(book);
		}
		return bookRepository.save(newBook);

	}

	public String calculatePrice(String barcode) {
		Book book = findByBarcode(barcode);
		if (book != null) {
		BigDecimal totalPrice = book.getPrice().multiply(new BigDecimal(book.getQuantity()));
		return totalPrice.toString();
		}
		else { return "Could not find book with barcode: " + barcode;
	}
	}

}
