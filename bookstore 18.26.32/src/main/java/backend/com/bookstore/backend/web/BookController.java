package backend.com.bookstore.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import backend.com.bookstore.backend.domain.Book;
import backend.com.bookstore.backend.repository.BookRepository;
import backend.com.bookstore.backend.repository.CategoryRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

     @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }
     @GetMapping("/addbook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book()); 
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }
@PostMapping("/addbook")
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book); 
        return "redirect:/booklist"; 
    }
    @GetMapping("/editbook/{isbn}")
    public String showEditBookForm(@PathVariable("isbn") String isbn, Model model) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ISBN: " + isbn));
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryRepository.findAll());
        return "editbook";
    }
    @PostMapping("/editbook")
    public String editBook(@ModelAttribute Book book) {
        bookRepository.save(book); 
        return "redirect:/booklist"; 
    }
    @GetMapping("/deletebook/{isbn}")
    public String deleteBook(@PathVariable("isbn") String isbn) {
        bookRepository.deleteById(isbn); 
        return "redirect:/booklist"; 
    }
}
