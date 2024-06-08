package bookshelf.book.dto;

import bookshelf.book.entity.Book;
import bookshelf.book.entity.BookCategory;

import java.time.LocalDate;

public class CreateBookRequest {

    private String title;
    private String author;
    private String isbn;
    private LocalDate purchaseDate;
    private BookCategory bookCategory;

    public CreateBookRequest(
            final String title,
            final String author,
            final String isbn,
            final LocalDate purchaseDate,
            final BookCategory bookCategory
    ) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.purchaseDate = purchaseDate;
        this.bookCategory = bookCategory;
    }

    public Book toEntity() {
        return new Book(
                this.title,
                this.author,
                this.isbn,
                this.purchaseDate,
                this.bookCategory
        );
    }
}
