package pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingInfo;

import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.AuthorInfoForBookDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.PublisherInfoForBookDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.SharePointEmailDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook.SharePointSearchDto;
import pl.edu.pg.booksharing.Booksharing.model.Genre;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BorrowedBookInfoDto {

    private Long id;

    private String title;

    private String isbn;

    private List<AuthorInfoForBookDto> authors = new ArrayList<>();

    private PublisherInfoForBookDto publisher;

    private SharePointSearchDto sharepoint;




    public BorrowedBookInfoDto() {
    }

    public BorrowedBookInfoDto(String title, String isbn, List<AuthorInfoForBookDto> authors,
                            PublisherInfoForBookDto publisher, Long id) {
        this.title = title;
        this.isbn = isbn;
        this.authors = authors;
        this.publisher = publisher;
        this.id = id;
    }



    public PublisherInfoForBookDto getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherInfoForBookDto publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<AuthorInfoForBookDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorInfoForBookDto> authors) {
        this.authors = authors;
    }

    public SharePointSearchDto getSharepoint() {
        return sharepoint;
    }

    public void setSharepoint(SharePointSearchDto sharepoint) {
        this.sharepoint = sharepoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
