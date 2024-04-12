package org.example.finalproject;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a book
 */
@Entity(name = "titles")
public class Book {
    @Id
    private String isbn;
    private String title;
    private int editionNumber;
    private String copyRight;

    @ManyToMany
    @JoinTable(
            name = "author_isbn",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Author> authorList = new ArrayList<>();

    /**
     * Constructor for a book
     * @param isbn isbn of the book being created
     * @param title title of the book being created
     * @param editionNumber edition number of the book being created
     * @param copyRight copy right of the book being created
     */

    /**
     * @return the isbn
     */
    public String getISBN() {
        System.out.printf("isbn = %s", isbn);
        return isbn;
    }

    /**
     * Sets a new isbn for the book
     * @param isbn new isbn for the book
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a new title for the book
     * @param title new title for the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the edition number
     */
    public int getEditionNumber() {
        return editionNumber;
    }

    /**
     * Sets a new edition number for the book
     * @param editionNumber new edition number for the book
     */
    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    /**
     * @return the copy right
     */
    public String getCopyright() {
        return copyRight;
    }

    /**
     * Sets a new copy right for the book
     * @param copyRight new copy right for the book
     */
    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    /**
     * @return the authors of the book
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * Sets the authors
     * @param authors
     */
    public void setAuthorList(List<Author> authors) {
        this.authorList = authors;
    }

    /**
     * Append every author in the authors list into a string
     * @param authorList
     * @return
     */
    public String getAppendedAuthors(List<Author> authorList) {
        StringBuilder authors = new StringBuilder();
        for (Author author : authorList) {
            authors.append(author.getFirstName()).append(" ").append(author.getLastName()).append(", ");
        }
        return authors.toString();
    }

    /**
     * Adds an author to the book
     * @param author author to be added to the book
     */
//    public void addAuthor(Author author) {
//        authors.add(author);
//    }
}

