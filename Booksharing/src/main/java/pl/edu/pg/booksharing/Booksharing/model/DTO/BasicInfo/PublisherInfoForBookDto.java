package pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo;

public class PublisherInfoForBookDto {

    private String name;


    public PublisherInfoForBookDto() {
    }

    public PublisherInfoForBookDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
