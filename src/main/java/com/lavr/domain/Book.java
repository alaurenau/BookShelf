package com.lavr.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lavr on 5/27/15.
 * book domain object
 */

@Entity
@Table(name = "Book")
@NamedQueries({
        @NamedQuery(name = "Book.findByTitle",
                query = "select c from Book c where c.title = :title"),
        @NamedQuery(name = "Book.findByAuthor",
                query = "select c from Book c where c.author = :author"),
        @NamedQuery(name = "Book.findById",
                query = "select c from Book c where c.bookId = :bookId"),
})
public class Book implements Serializable {
    private Long bookId;
    private String title;
    private String author;
    private User user;
    private BookUsage bookUsage = new BookUsage(this);
    private Rating rating = new Rating(this);

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "BOOK_ID")
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return "Book - " + bookId + title + author + user;
    }

    @JsonManagedReference
    @OneToOne(mappedBy = "book", fetch = FetchType.EAGER, orphanRemoval=true,
            cascade=CascadeType.ALL)
    public BookUsage getBookUsage() {
        return bookUsage;
    }

    public void setBookUsage(BookUsage bookUsage) {
        this.bookUsage = bookUsage;
    }


    @JsonManagedReference
    @OneToOne(mappedBy = "book", fetch = FetchType.EAGER, orphanRemoval=true,
            cascade=CascadeType.ALL)
    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

}
