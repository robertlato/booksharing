package pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook;

public class BookBorrowingDto {

    private Long id;

    public BookBorrowingDto(Long id) {
        this.id = id;
    }

    public BookBorrowingDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
