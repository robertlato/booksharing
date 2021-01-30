package pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook;

public class SharePointBorrowingDto {

    private Long id;

    public SharePointBorrowingDto() {
    }

    public SharePointBorrowingDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
