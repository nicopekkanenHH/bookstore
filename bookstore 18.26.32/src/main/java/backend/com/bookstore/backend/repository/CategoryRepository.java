package backend.com.bookstore.backend.repository;
import org.springframework.data.repository.CrudRepository;
import backend.com.bookstore.backend.domain.Category;
public interface CategoryRepository extends CrudRepository <Category, String>{

}
