package backend.com.bookstore.backend.web;

import backend.com.bookstore.backend.domain.Book;
import backend.com.bookstore.backend.repository.BookRepository;
import backend.com.bookstore.backend.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/books")
public class BookRestController {

    private static final Logger log = LoggerFactory.getLogger(BookRestController.class);

@Autowired
 BookRepository bookRepository;
@Autowired
CategoryRepository categoryRepository;
   

    
    @GetMapping
    public Iterable<Book> getAllBooks() {
        log.info("Fetch all books");
        return bookRepository.findAll();
    }

   
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id) {
        log.info("Fetch book with ID: " + id);
        return bookRepository.findById(id).orElse(null);
    }

    @PostMapping
    Book newBook(@RequestBody Book newBook){
        log.info("save new book" + newBook);
        return bookRepository.save(newBook);
    }

     @PutMapping("/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody Book updatedBook) {
        log.info("Update book with ID: " + id);
        updatedBook.setIsbn(id); 
        return bookRepository.save(updatedBook);
    }

    @DeleteMapping("/{id}")
    public Iterable<Book> deleteBook(@PathVariable String id) {
        log.info("Delete book with ID: " + id);
        bookRepository.deleteById(id);
        return bookRepository.findAll();
    }
}
