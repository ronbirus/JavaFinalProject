package org.example.finalproject;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent an author
 */
@Entity(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorID;
    private String firstName;
    private String lastName;

    @ManyToMany (mappedBy = "authorList")
    @JsonBackReference
    private List<Book> books = new ArrayList<>();

    /**
     * Sets author id
     * @param authorID
     */
    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }


    /**
     * @return the authorID
     */

    public int getAuthorID() {
        return authorID;
    }

    /**
     * Gets the first name of the author
     * @return firstName of the author
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets a new first name for the author
     * @param firstName new first name for the author
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the author
     * @return lastName of the author
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets a new last name for the author
     * @param lastName new last name for the author
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the books of the author
     */
    public List<Book> getBooks() {

        return books;
    }

    /**
     * Sets books
     * @param books
     */
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getAppendedBooks(List<Book> bookList) {
        StringBuilder bookNames = new StringBuilder();
        for(Book book: bookList) {
            bookNames.append(book.getTitle()).append(", ");
        }
        return bookNames.toString();
    }

    /**
     * Adds a book to the author's list of books
     * @param book book to be added to the author's list of books
     */
//    public void addBook(Book book) {
//        books.add(book);
//    }
}
