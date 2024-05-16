package bookshelf;

public class UpdateBookshelfRequest {

    private String name;
    private int floor;

    public UpdateBookshelfRequest(String name, int floor) {
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
