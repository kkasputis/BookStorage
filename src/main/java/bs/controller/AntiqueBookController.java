package bs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import bs.entity.AntiqueBook;
import bs.service.AntiqueBookService;

@RestController
@RequestMapping("/antique")
public class AntiqueBookController {
	@Autowired
	AntiqueBookService antiqueBookService;


	@RequestMapping(method = RequestMethod.POST)
	String newBook(@RequestBody AntiqueBook book) {
		return antiqueBookService.save(book);
	}

	@RequestMapping(value = "/{barcode}", method = RequestMethod.GET)
	AntiqueBook bookInfo(@PathVariable String barcode) {

		return antiqueBookService.findByBarcode(barcode);
	}

	@RequestMapping(value = "/{barcode}", method = RequestMethod.PUT)
	public AntiqueBook updateBook(@RequestBody AntiqueBook book, @PathVariable String barcode) {
		return antiqueBookService.update(book, barcode);

	}
	@RequestMapping(method = RequestMethod.GET)
	List<AntiqueBook> allAntiqueBooks() {
		return antiqueBookService.findAll();
	}

}
