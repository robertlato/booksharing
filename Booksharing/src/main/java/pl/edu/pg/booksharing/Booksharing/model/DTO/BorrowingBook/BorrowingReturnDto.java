package pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook;

public class BorrowingReturnDto {

    private Long id;

    public BorrowingReturnDto() {
    }

    public BorrowingReturnDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
