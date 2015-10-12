package com.lavr.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lavr on 5/27/15.
 * user domain object
 */

@Entity
@Table(name = "User")
@NamedQueries({
        @NamedQuery(name = "User.findById",
                query = "select c from User c where c.userId = :userId"),
        @NamedQuery(name = "User.findByName",
                query = "select c from User c where c.firstName = :firstName and " +
                        "c.lastName = :lastName"),
})
public class User implements Serializable {
    private Long userId;
    private String firstName;
    private String lastName;
    private Set<Book> books = new HashSet<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "USER_ID")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        book.setUser(this);
        book.getBookUsage();
        getBooks().add(book);

    }

    public void removeBook(Book book) {
        getBooks().remove(book);
    }
}
