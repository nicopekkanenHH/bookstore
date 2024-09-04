package backend.com.bookstore.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("/index")
    public String index(Model model) {
        // This method will handle GET requests to /index
        model.addAttribute("title", "Welcome to the Bookstore");
        return "index";
    }
}
