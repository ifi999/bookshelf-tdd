package bookshelf.booshelf.entity;

import bookshelf.book.entity.Book;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Bookshelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int floor;
    @OneToMany(mappedBy = "bookshelf")
    private List<Book> bookList = new ArrayList<>();

    protected Bookshelf() {
    }

    public Bookshelf(final String name, final int floor) {
        Assert.hasText(name, "책장 이름은 필수값입니다.");
        Assert.isTrue(floor > 0, "책장 층수는 0보다 커야합니다.");

        this.name = name;
        this.floor = floor;
    }

    public void updateBookshelfDetails(final String name, final int floor) {
        Assert.hasText(name, "책장 이름은 필수값입니다.");
        Assert.isTrue(floor > 0, "책장 층수는 0보다 커야합니다.");

        this.name = name;
        this.floor = floor;
    }

    public void addBook(final Book book) {
        this.bookList.add(book);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFloor() {
        return floor;
    }

    public List<Book> getBookList() {
        return bookList;
    }

}
