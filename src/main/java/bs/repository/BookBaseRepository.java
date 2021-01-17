package bs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import bs.entity.Book;

@NoRepositoryBean
public interface BookBaseRepository<T extends Book> 
extends JpaRepository<T, Long> {
	Optional<T> findFirstByBarcode(String barcode);
	boolean existsByBarcode(String barcode);
}