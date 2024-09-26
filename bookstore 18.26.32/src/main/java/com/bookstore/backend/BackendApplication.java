package com.bookstore.backend;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.backend.domain.AppUser;

import com.bookstore.backend.domain.Book;
import com.bookstore.backend.domain.Category;
import com.bookstore.backend.repository.BookRepository;
import com.bookstore.backend.repository.CategoryRepository;
import com.bookstore.backend.repository.AppUserRepository;
import java.util.Set;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData( CategoryRepository categoryRepository, 
	BookRepository bookRepository, 
	AppUserRepository appUserRepository 
	 ) {
		return (args) -> {

			Category fiction = new Category("F1","Fiction");
			Category fantasy = new Category("F2","Fantasy");
			Category classic = new Category("F3","Classic");

			categoryRepository.save(fiction);
			categoryRepository.save(fantasy);
			categoryRepository.save(classic);

			bookRepository.save(new Book("The Hobbit", "J.R.R. Tolkien", "978-0618968633", 1937, fantasy));
			bookRepository.save(new Book("1984", "George Orwell", "978-0451524935", 1949, fiction));
			bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee", "978-0060935467", 1960, fiction));
			bookRepository.save(new Book("The Catcher in the Rye", "J.D. Salinger", "978-0316769488", 1951, classic));
			bookRepository.save(new Book("Pride and Prejudice", "Jane Austen", "978-0141040349", 1813, classic));

			AppUser user1 = new AppUser("user", "user", Set.of("USER"), "user@example.com");
            AppUser user2 = new AppUser("admin", "admin", Set.of("ADMIN"), "admin@example.com");

            appUserRepository.save(user1);
            appUserRepository.save(user2);
		};
	}
    
}