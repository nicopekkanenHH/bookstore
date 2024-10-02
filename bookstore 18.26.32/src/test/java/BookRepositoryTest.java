

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookstore.backend.BackendApplication;
import com.bookstore.backend.domain.Book;
import com.bookstore.backend.domain.Category;
import com.bookstore.backend.repository.BookRepository;
import com.bookstore.backend.repository.CategoryRepository;

@SpringBootTest(classes = BackendApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void addBookTest() {
        Category category = categoryRepository.findById("F1").orElseGet(() -> {
            Category newCategory = new Category("F1", "Fiction");
            return categoryRepository.save(newCategory);
        });

        Book newBook = new Book("Test Book", "Test Author", "1234567890", 2023, category);
        bookRepository.save(newBook);

        Book foundBook = bookRepository.findById("1234567890").orElse(null);
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("Test Book");
        assertThat(foundBook.getAuthor()).isEqualTo("Test Author");
    }

    @Test
    public void deleteBookTest() {
        Category category = categoryRepository.findById("F1").orElseGet(() -> {
            Category newCategory = new Category("F1", "Fiction");
            return categoryRepository.save(newCategory);
        });

        Book bookToDelete = new Book("Delete Me", "Delete Author", "0987654321", 2023, category);
        bookRepository.save(bookToDelete);

        assertThat(bookRepository.findById("0987654321").orElse(null)).isNotNull();

        bookRepository.deleteById("0987654321");

        assertThat(bookRepository.findById("0987654321").orElse(null)).isNull();
    }

    @Test
    public void editBookTest() {
        Category category = categoryRepository.findById("F1").orElseGet(() -> {
            Category newCategory = new Category("F1", "Fiction");
            return categoryRepository.save(newCategory);
        });

        Book bookToEdit = new Book("Original Title", "Original Author", "1122334455", 2023, category);
        bookRepository.save(bookToEdit);

        
        Book retrievedBook = bookRepository.findById("1122334455").orElse(null);
        assertThat(retrievedBook).isNotNull();

        retrievedBook.setTitle("Updated Title");
        retrievedBook.setAuthor("Updated Author");
        bookRepository.save(retrievedBook);

        Book updatedBook = bookRepository.findById("1122334455").orElse(null);
        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedBook.getAuthor()).isEqualTo("Updated Author");
    }
}
