package bs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import bs.entity.Book;
import bs.service.BookServiceImpl;
import bs.service.PriceService;

@RestController
public class BookController {
	
	@Autowired
	BookServiceImpl bookService;
	@Autowired
	PriceService priceService;

	@RequestMapping(value = "book/save", method = RequestMethod.POST)
	public String newBook(@RequestBody Book book) {
		return bookService.save(book);
	}

	@RequestMapping(value = "book/{barcode}", method = RequestMethod.GET)
	public Book bookInfo(@PathVariable String barcode) {

		return bookService.findByBarcode(barcode);
	}

	@RequestMapping(value = "book/update/{barcode}", method = RequestMethod.PUT)
	public Book updateBook(@RequestBody Book book, @PathVariable String barcode) {
		return bookService.update(book, barcode);

	}
	
	@RequestMapping(value = "price/{barcode}", method = RequestMethod.GET)
	public String price(@PathVariable String barcode) {
		return priceService.calculate(barcode);
	}

}
