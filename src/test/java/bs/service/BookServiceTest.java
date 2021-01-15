package bs.service;


import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import bs.entity.AntiqueBook;
import bs.entity.Book;
import bs.entity.ScienceJournal;
import bs.repository.AntiqueBookRepository;
import bs.repository.BookRepository;
import bs.repository.ScienceJournalRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookServiceTest {

	@InjectMocks
	BookServiceImpl bookService;
	@InjectMocks
	PriceService priceService;
	@InjectMocks
	AntiqueBookService antiqueBookService;
	@InjectMocks
	ScienceJournalService scienceJournalService;
	@Mock
	BookRepository bookRepository;
	@Mock
	AntiqueBookRepository antiqueBookRepository;
	@Mock
	ScienceJournalRepository scienceJournalRepository;

	

	@Test
	public void calculateBook() {
		Book book = new Book("Book Name", "Author", "barcode,", 10, new BigDecimal(3));
		Mockito.when(bookRepository.findFirstByBarcode("barcode")).thenReturn(Optional.of(book));
		String result = bookService.calculatePrice("barcode");
		assertEquals(result,"30");
	}
	@Test
	public void calculateAntiqueBook() {
		AntiqueBook book = new AntiqueBook("Book Name", "Author", "barcode,", 10, new BigDecimal(3));
		book.setYear(1412);
		Mockito.when(antiqueBookRepository.findFirstByBarcode("barcode")).thenReturn(Optional.of(book));
		String result = antiqueBookService.calculatePrice("barcode");
		assertEquals(result, "1827.00");
	}
	@Test
	public void calculateJournal() {
		ScienceJournal book = new ScienceJournal("Book Name", "Author", "barcode,", 10, new BigDecimal(3));
		book.setScienceIndex(5);
		Mockito.when(scienceJournalRepository.findFirstByBarcode("barcode")).thenReturn(Optional.of(book));
		String result = scienceJournalService.calculatePrice("barcode");
		assertEquals(result, "150");
	}
}
