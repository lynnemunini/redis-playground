package com.grayseal.bookstore.repositories;

import com.grayseal.bookstore.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {
}
