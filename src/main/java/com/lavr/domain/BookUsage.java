package com.lavr.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lavr on 5/27/15.
 * books_usage domain object
 */

@Entity
@Table(name = "Book_usage")
public class BookUsage implements Serializable {
    private Book book;
    private Date usedSince;
    private Date usedUntil;

    public BookUsage() {
    }

    public BookUsage(Book book) {
        this.book = book;
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


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USED_SINCE")
    public Date getUsedSince() {
        return usedSince;
    }

    public void setUsedSince(Date usedSince) {
        this.usedSince = usedSince;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USED_UNTIL")
    public Date getUsedUntil() {
        return usedUntil;
    }

    public void setUsedUntil(Date usedUntil) {
        this.usedUntil = usedUntil;
    }


}
