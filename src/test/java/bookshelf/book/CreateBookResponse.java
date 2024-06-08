package bookshelf.book;

import java.time.LocalDate;

public class CreateBookResponse {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private LocalDate purchaseDate;
    private BookCategory bookCategory;

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
