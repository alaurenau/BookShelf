package com.lavr.data;

import com.lavr.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lavr on 5/27/15.
 * dao interface
 */

@Repository
public interface BookDao {
    List<Book> findAllBooks();

    List findBookInfo();
    List findRatingInfo();

    Book findByTitle(String title);
    Book findByAuthor(String author);
    Book findById(Long id);

    Book save(Book book);
    void delete(Book book);






}
