package com.lavr.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lavr on 5/28/15.
 * rating dao
 */

@Entity
@Table(name = "Rating")
public class Rating implements Serializable {
    private Book book;
    private Long rating;

    public Rating() {
    }

    public Rating(Book book) {
        this.book = book;
        this.rating = (long) 0;
    }

    @Id
    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOK_ID")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Column(name = "RATING")
    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public void incRating() {
        this.rating++;
    }
}
