package backend.com.bookstore.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import backend.com.bookstore.backend.repository.BookRepository;

@Controller
public class BookController {

    @GetMapping("/index")
    public String index(Model model) {
       
        model.addAttribute("title", "Welcome to the Bookstore");
        return "index";
    }
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }
}
