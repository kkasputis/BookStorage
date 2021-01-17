package bs.front.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import bs.entity.AntiqueBook;
import bs.entity.Book;
import bs.entity.ScienceJournal;
import bs.front.service.FrontService;
import bs.repository.BookRepository;

@Controller
public class FrontController {
	@Autowired
	FrontService service;
	@Autowired
	BookRepository bookRepository;


	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) throws IOException {
		model = prepareIndex(model);
		return "index";
	}

	@RequestMapping(value = "/antiquelist",method = RequestMethod.GET)
	public String antiqueBookList(Model model) throws IOException {
		model = prepareIndex(model);
		model.addAttribute("books", service.findAllAntique());
		return "index";
	}
	@RequestMapping(value = "/sciencelist",method = RequestMethod.GET)
	public String scienceJournalList(Model model) throws IOException {
		model = prepareIndex(model);
		model.addAttribute("books", service.findAllScience());
		return "index";
	}
	@RequestMapping(value = "/bookinfo/{barcode}", method = RequestMethod.GET)
	public String bookInfo(Model model, @PathVariable String barcode) throws IOException {
		Book book = service.findBook(barcode);
		model.addAttribute("book", book);
		String className = book.getClass().getName();
		switch (className) {
		case "bs.entity.AntiqueBook":
			return "antique";
		case "bs.entity.ScienceJournal":
			return "science";
		case "bs.entity.Book":
			return "book";
		default:
			return "book";
		}
	}

	@RequestMapping(value = "/front/book/save", method = RequestMethod.POST)
	public String addBook(Model model, @ModelAttribute("newBook") Book book) throws IOException {
		model.addAttribute("info", service.save(book, "books"));
		model = prepareIndex(model);
		return "index";

	}

	@RequestMapping(value = "/front/antique/save", method = RequestMethod.POST)
	public String addAntique(Model model, @ModelAttribute("newAntiqueBook") AntiqueBook book) throws IOException {
		model.addAttribute("info", service.save(book, "antique"));
		model = prepareIndex(model);
		return "index";

	}

	@RequestMapping(value = "/front/science/save", method = RequestMethod.POST)
	public String addScience(Model model, @ModelAttribute("newScienceJournal") ScienceJournal journal)
			throws IOException {
		model.addAttribute("info", service.save(journal, "science"));
		model = prepareIndex(model);
		return "index";

	}


	@RequestMapping(value = "/front/book/update", method = RequestMethod.POST)
	public String updateBook(Model model, @ModelAttribute("book") Book book) throws IOException {
		model.addAttribute("book", book);
		service.update(book, "book");
		return "redirect:/bookinfo/" + book.getBarcode();
	}

	@RequestMapping(value = "/front/antique/update", method = RequestMethod.POST)
	public String updateAntiqueBook(Model model, @ModelAttribute("book") AntiqueBook book) throws IOException {
		model.addAttribute("book", book);
		service.update(book, "antique");
		return "redirect:/bookinfo/" + book.getBarcode();
	}

	@RequestMapping(value = "/front/science/update", method = RequestMethod.POST)
	public String updateScienceJournal(Model model, @ModelAttribute("book") ScienceJournal journal) throws IOException {
		model.addAttribute("book", journal);
		service.update(journal, "science");
		return "redirect:/bookinfo/" + journal.getBarcode();
	}
	Model prepareIndex(Model model) throws IOException {
		model.addAttribute("books", service.findAllBooks());
		model.addAttribute("newBook", new Book());
		model.addAttribute("newAntiqueBook", new AntiqueBook());
		model.addAttribute("newScienceJournal", new ScienceJournal());
		return model;
	}
}
