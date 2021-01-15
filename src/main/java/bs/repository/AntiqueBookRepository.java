package bs.repository;

import org.springframework.transaction.annotation.Transactional;
import bs.entity.AntiqueBook;



@Transactional
public interface AntiqueBookRepository  extends BookBaseRepository<AntiqueBook> {

}
