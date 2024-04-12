package org.example.finalproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path ="api/v1")
public class MainController {
    public static final String BOOK = "/books";
    public static final String AUTHOR = "/authors";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Get all books
     * @return
     */
    @GetMapping (path = BOOK)
    public @ResponseBody
    Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    /**
     * Add a new book
     * @param isbn
     * @param title
     * @param editionNumber
     * @param copyright
     * @param author_id
     * @return
     */
    @PostMapping(path = BOOK)
    public @ResponseBody
    String addNewBook (@RequestParam String isbn, @RequestParam String title, @RequestParam int editionNumber, @RequestParam String copyright, @RequestParam Integer author_id){
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setEditionNumber(editionNumber);
        book.setCopyRight(copyright);
        Optional<Author> author = authorRepository.findById(author_id);
        if(author.isPresent()){
            book.getAuthorList().add(author.get());
            bookRepository.save(book);
            return "Saved";
        }
        return "Author not found";
    }

    /**
     * Get a book by isbn
     * @param isbn
     * @return
     */
    @GetMapping (path = BOOK + "/{isbn}")
    public @ResponseBody
    Book getBookWithId(@PathVariable String isbn){
        return bookRepository.findBookByIsbn(isbn);
    }

    @DeleteMapping (path = BOOK + "/{isbn}")
    public @ResponseBody
    String deleteBook(@PathVariable String isbn){
        Book book = bookRepository.findBookByIsbn(isbn);
        if(book != null){
            bookRepository.delete(book);
            return "deleted";
        }
        return "book not found";
    }

    /**
     * Update a book
     * @param isbn
     * @param title
     * @param editionNumber
     * @param copyRight
     * @return
     */
    @PutMapping(path = BOOK + "/{isbn}")
    public @ResponseBody
    String updateBook(@PathVariable String isbn, @RequestParam String title, @RequestParam int editionNumber, @RequestParam String copyRight){
        Book book = bookRepository.findBookByIsbn(isbn);
        if(book != null){
            book.setTitle(title);
            book.setEditionNumber(editionNumber);
            book.setCopyRight(copyRight);
            bookRepository.save(book);
            return "updated";
        }
        return "book not found";
    }

    /**
     * Get all authors
     * @return
     */
    @GetMapping (path = AUTHOR)
    public @ResponseBody
    Iterable<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    /**
     * Get an author by id
     * @param author_id
     * @return
     */
    @GetMapping (path = AUTHOR + "/{author_id}")
    public @ResponseBody
    Optional<Author> getAuthorWithId(@PathVariable Integer author_id){
        return authorRepository.findById(author_id);
    }

    /**
     * Add a new author
     * @param author_id
     * @param firstName
     * @param lastName
     * @return
     */
    @PostMapping(path = AUTHOR)
    public @ResponseBody
    String addNewAuthor (@RequestParam Integer author_id, @RequestParam String firstName, @RequestParam String lastName){
        Author author = new Author();
        author.setAuthorID(author_id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepository.save(author);
        return "Saved";
    }

    /**
     * Update an author
     * @param author_id
     * @param firstName
     * @param lastName
     * @return
     */
    @PutMapping(path = AUTHOR + "/{author_id}")
    public @ResponseBody
    String updateAuthor(@PathVariable Integer author_id, @RequestParam String firstName, @RequestParam String lastName){
        Optional<Author> author = authorRepository.findById(author_id);
        if(author.isPresent()){
            Author author1 = author.get();
            author1.setFirstName(firstName);
            author1.setLastName(lastName);
            authorRepository.save(author1);
            return "updated";
        }
        return "author not found";
    }

    /**
     * Delete an author
     * @param author_id
     * @return
     */
    @DeleteMapping(path = AUTHOR + "/{author_id}")
    public @ResponseBody
    String deleteAuthor(@PathVariable Integer author_id){
        Optional<Author> author = authorRepository.findById(author_id);
        if(author.isPresent()){
            List<Book> booksList = bookRepository.findBooksByAuthorListContaining(author.get());
            for(Book book: booksList){
                book.getAuthorList().remove(author.get());
                authorRepository.delete(author.get());
                bookRepository.save(book);
            }
            return "deleted";
        }
        return "author not found";
    }
}

