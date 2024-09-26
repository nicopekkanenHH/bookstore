package com.bookstore.backend.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bookstore.backend.domain.Category;
@Repository
public interface CategoryRepository extends CrudRepository <Category, String>{

}
