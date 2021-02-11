package pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo;

public class GenreInfoForBookDto {

    private String name;

    public GenreInfoForBookDto() {
    }

    public GenreInfoForBookDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
