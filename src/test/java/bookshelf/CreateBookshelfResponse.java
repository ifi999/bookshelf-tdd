package bookshelf;

public class CreateBookshelfResponse {

    private Long id;
    private String name;
    private int floor;

    public CreateBookshelfResponse(final Long id, final String name, final int floor) {
        this.id = id;
        this.name = name;
        this.floor = floor;
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
