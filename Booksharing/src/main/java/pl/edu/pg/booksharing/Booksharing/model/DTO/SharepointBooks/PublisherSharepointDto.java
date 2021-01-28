package pl.edu.pg.booksharing.Booksharing.model.DTO.SharepointBooks;

public class PublisherSharepointDto {

    private String name;

    public PublisherSharepointDto() {
    }

    public PublisherSharepointDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
