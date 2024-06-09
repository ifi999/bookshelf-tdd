package bookshelf.book.dto;

import bookshelf.book.entity.Book;
import bookshelf.book.entity.BookCategory;

import java.time.LocalDate;

public class GetBookResponse {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private LocalDate purchaseDate;
    private BookCategory bookCategory;

    public GetBookResponse(
            final Long id,
            final String title,
            final String author,
            final String isbn,
            final LocalDate purchaseDate,
            final BookCategory bookCategory
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.purchaseDate = purchaseDate;
        this.bookCategory = bookCategory;
    }

    public static GetBookResponse toDto(final Book book) {
        return new GetBookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPurchaseDate(),
                book.getBookCategory()
        );
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
