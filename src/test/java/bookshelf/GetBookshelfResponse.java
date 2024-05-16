package bookshelf;

public class GetBookshelfResponse {

    private Long id;
    private String name;
    private int floor;

    public GetBookshelfResponse(Long id, String name, int floor) {
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
