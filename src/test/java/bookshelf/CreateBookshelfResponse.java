package bookshelf;

public class CreateBookshelfResponse {

    private Long id;
    private String name;
    private int floor;

    public CreateBookshelfResponse(Bookshelf bookshelf) {
        this.id = bookshelf.getId();
        this.name = bookshelf.getName();
        this.floor = bookshelf.getFloor();
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

}
