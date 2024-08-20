package com.grayseal.bookstore.repositories;

import com.grayseal.bookstore.models.BookRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRatingRepository extends CrudRepository<BookRating, String> {
}
