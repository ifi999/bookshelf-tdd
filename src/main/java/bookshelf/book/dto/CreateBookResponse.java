package bookshelf.book.dto;

import bookshelf.book.entity.Book;
import bookshelf.book.entity.BookCategory;

import java.time.LocalDate;

public class CreateBookResponse {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private LocalDate purchaseDate;
    private BookCategory bookCategory;

    public CreateBookResponse(final Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.purchaseDate = book.getPurchaseDate();
        this.bookCategory = book.getBookCategory();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

}
