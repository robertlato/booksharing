package pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook;

public class PublisherSearchDto {

    private String name;


    public PublisherSearchDto() {
    }

    public PublisherSearchDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
