package bookshelf.booshelf.dto;

public class CreateBookshelfRequest {

    private String name;
    private int floor;

    public CreateBookshelfRequest(final String name, final int floor) {
        this.name = name;
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public int getFloor() {
        return floor;
    }

}
