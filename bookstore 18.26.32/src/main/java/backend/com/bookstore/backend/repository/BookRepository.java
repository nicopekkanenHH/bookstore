package backend.com.bookstore.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import backend.com.bookstore.backend.domain.Book;

public interface BookRepository extends JpaRepository<Book, String> {
}
