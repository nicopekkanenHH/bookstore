package backend.com.bookstore.backend;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import backend.com.bookstore.backend.domain.Book;
import backend.com.bookstore.backend.domain.Category;
import backend.com.bookstore.backend.repository.BookRepository;
import backend.com.bookstore.backend.repository.CategoryRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {

			Category fiction = new Category("Fiction");
			Category fantasy = new Category("Fantasy");
			Category classic = new Category("Classic");

			categoryRepository.save(fiction);
			categoryRepository.save(fantasy);
			categoryRepository.save(classic);

			bookRepository.save(new Book("The Hobbit", "J.R.R. Tolkien", "978-0618968633", 1937, fantasy));
			bookRepository.save(new Book("1984", "George Orwell", "978-0451524935", 1949, fiction));
			bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee", "978-0060935467", 1960, fiction));
			bookRepository.save(new Book("The Catcher in the Rye", "J.D. Salinger", "978-0316769488", 1951, classic));
			bookRepository.save(new Book("Pride and Prejudice", "Jane Austen", "978-0141040349", 1813, classic));
		};
	}
    
}