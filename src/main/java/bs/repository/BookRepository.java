package bs.repository;

import org.springframework.transaction.annotation.Transactional;
import bs.entity.Book;

@Transactional
public interface BookRepository extends BookBaseRepository<Book> {

}
