package bookshelf.book;

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

}
