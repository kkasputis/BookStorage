package bs.controller;



import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import bs.entity.Book;
import bs.repository.BookRepository;
import bs.service.BookServiceImpl;
import bs.service.PriceService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
	@InjectMocks
	BookController bookController;
	@Mock
	BookRepository bookRepository;

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	PriceService priceService;

	@MockBean
	private BookServiceImpl bookService;
	
	
	
	
	Book mockBook = new Book("Book Name", "Author", "barcode,", 10, new BigDecimal(3));
	String exampleBookJson = "{\"name\":\"Book Name\",\"author\":\"Author\",\"barcode\":\"barcode\",\"quantity\":\"10\",\"price\":\"3\"}";

	@Test
	public void testAddBook() throws Exception {
		String expected = "Saved";
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(bookService.save(any(Book.class))).thenReturn("Saved");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books").content(exampleBookJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.ALL);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(expected, result.getResponse().getContentAsString());
	}

	@Test
	public void findBookByBarcode() throws Exception {
		when(bookService.findByBarcode("barcode")).thenReturn(mockBook);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book/barcode").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"name\":\"Book Name\",\"author\":\"Author\",\"barcode\":\"barcode,\",\"quantity\":10,\"price\":3}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void price() throws Exception {
		String expected = "30";
		when(priceService.calculate("barcode")).thenReturn("30");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/price/barcode").accept(MediaType.ALL);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(expected, result.getResponse().getContentAsString());

	}

}
