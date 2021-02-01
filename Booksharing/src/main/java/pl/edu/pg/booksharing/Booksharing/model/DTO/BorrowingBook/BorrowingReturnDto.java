package pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook;

public class BorrowingReturnDto {

    private BookBorrowingDto book;

    public BorrowingReturnDto() {
    }

    public BorrowingReturnDto(BookBorrowingDto book) {
        this.book = book;
    }

    public BookBorrowingDto getBook() {
        return book;
    }

    public void setBook(BookBorrowingDto book) {
        this.book = book;
    }
}
