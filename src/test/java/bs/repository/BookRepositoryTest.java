package bs.repository;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import bs.entity.Book;

@RunWith(SpringRunner.class)
@DataJpaTest

public class BookRepositoryTest {
	
	
	
	@Autowired
    private BookRepository bookRepository;

	
	  @Test
	    public void testRepository() {
		  Book book = new Book("Book Name", "Author", "barcode,", 10, new BigDecimal(3));
	        
	        bookRepository.save(book);
	        
	        Book bookTest = bookRepository.findFirstByBarcode(book.getBarcode()).get();
	        assertEquals(bookTest.getBarcode(), book.getBarcode());
	        bookRepository.delete(bookTest);
	        bookTest = bookRepository.findFirstByBarcode(book.getBarcode()).orElse(null);
	        assertEquals(bookTest, null);
	    }
}
