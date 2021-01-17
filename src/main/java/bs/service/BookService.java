package bs.service;

import java.util.List;

public interface BookService<T> {


	public String save(T book);

	public T findByBarcode(String barcode);

	public T update(T newBook, String barcode);
	
	public String calculatePrice(String barcode);
	
	public List<T> findAll();

}
