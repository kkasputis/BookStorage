package bs.front.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import bs.entity.AntiqueBook;
import bs.entity.Book;
import bs.entity.ScienceJournal;

@Service
public class FrontService {

	public List<Book> findAllBooks() throws IOException {
		URL url = new URL("http://localhost:8080/books");
		BufferedReader br = setGetConnection(url);
		Gson g = new Gson();
		Type listType = new TypeToken<ArrayList<Book>>(){}.getType();
		List<Book> books = g.fromJson(br.readLine(), listType);		
	return books;
	
	}
	public List<AntiqueBook> findAllAntique() throws IOException {
		URL url = new URL("http://localhost:8080/antique");
		BufferedReader br = setGetConnection(url);
		Gson g = new Gson();
		Type listType = new TypeToken<ArrayList<AntiqueBook>>(){}.getType();
		List<AntiqueBook> books = g.fromJson(br.readLine(), listType);		
	return books;
	
	}
	public List<ScienceJournal> findAllScience() throws IOException {
		URL url = new URL("http://localhost:8080/science");
		BufferedReader br = setGetConnection(url);
		Gson g = new Gson();
		Type listType = new TypeToken<ArrayList<ScienceJournal>>(){}.getType();
		List<ScienceJournal> books = g.fromJson(br.readLine(), listType);		
	return books;
	
	}
	public Book findBook(String barcode) throws IOException {
		URL url = new URL("http://localhost:8080/book/" + barcode);
		BufferedReader br = setGetConnection(url);
		Gson gSon = new Gson();
		String responseJson = br.readLine();
		if (responseJson.contains("\"year\":")) {
		return gSon.fromJson(responseJson, AntiqueBook.class);
		}
		if (responseJson.contains("\"scienceIndex\":")) {
		return gSon.fromJson(responseJson, ScienceJournal.class);
		}
		else {
				return gSon.fromJson(responseJson, Book.class);
				
		}
	}
	public String save(Book book, String bookType) throws IOException {
		Gson gSon = new Gson();
		String jsonBook = gSon.toJson(book);
		URL url = new URL("http://localhost:8080/"+ bookType);
		HttpURLConnection connection = setPostConnection(url);
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		out.write(jsonBook);
		out.flush();
		out.close();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		return br.readLine();
	}
	public void update(Book book, String bookType) throws IOException {
		Gson gSon = new Gson();
		String jsonBook = gSon.toJson(book);
		URL url = new URL("http://localhost:8080/"+ bookType + "/" + book.getBarcode());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		connection.setRequestMethod("PUT");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		out.write(jsonBook);
		out.flush();
		out.close();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		br.readLine();
	}
	public HttpURLConnection setPostConnection(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		return connection;
	}
	public BufferedReader setGetConnection(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		return br;
	}
}
