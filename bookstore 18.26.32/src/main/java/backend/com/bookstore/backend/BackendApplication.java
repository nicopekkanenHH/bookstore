package backend.com.bookstore.backend;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import backend.com.bookstore.backend.domain.Book;
import backend.com.bookstore.backend.repository.BookRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
    public CommandLineRunner loadData(BookRepository repository) {
        return (args) -> {
            // Add some example data to the database
            repository.save(new Book("The Hobbit", "J.R.R. Tolkien", 1937, "978-0618968633", 25.99));
            repository.save(new Book("1984", "George Orwell", 1949, "978-0451524935", 19.99));
            repository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "978-0060935467", 15.99));
            repository.save(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "978-0316769488", 18.99));
            repository.save(new Book("Pride and Prejudice", "Jane Austen", 1813, "978-0141040349", 12.99));
        };
    }
    
}