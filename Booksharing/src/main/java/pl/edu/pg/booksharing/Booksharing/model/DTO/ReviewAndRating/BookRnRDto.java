package pl.edu.pg.booksharing.Booksharing.model.DTO.ReviewAndRating;

public class BookRnRDto {
    private Long id;

    public BookRnRDto(Long id) {
        this.id = id;
    }

    public BookRnRDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
