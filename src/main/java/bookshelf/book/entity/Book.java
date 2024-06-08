package bookshelf.book.entity;

import bookshelf.booshelf.entity.Bookshelf;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private LocalDate purchaseDate;
    @Enumerated(EnumType.ORDINAL)
    private BookCategory bookCategory;
    @ManyToOne
    @JoinColumn(name = "bookshelf_id")
    private Bookshelf bookshelf;

    protected Book() {
    }

    public Book(
            final String title,
            final String author,
            final String isbn,
            final LocalDate purchaseDate,
            final BookCategory bookCategory
    ) {
        Assert.hasText(title, "책 이름은 필수값입니다.");
        Assert.hasText(author, "저자는 필수값입니다.");
        Assert.hasText(isbn, "ISBN은 필수값입니다.");
        Assert.isTrue(LocalDate.now().isAfter(purchaseDate), "구매일은 현재보다 과거여야합니다.");
        Assert.notNull(bookCategory, "카테고리는 필수값입니다.");

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.purchaseDate = purchaseDate;
        this.bookCategory = bookCategory;
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
