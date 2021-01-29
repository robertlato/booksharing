package pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook;

import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.Publisher;
import pl.edu.pg.booksharing.Booksharing.model.SharePoint;

import java.util.ArrayList;
import java.util.List;

public class BookSearchDto {

    private String title;

    private PublisherSearchDto publisher;

    private List<AuthorSearchDto> authors = new ArrayList<>();

    private SharePointSearchDto sharePoint;


    public BookSearchDto() {
    }

    public BookSearchDto(String title, PublisherSearchDto publisher,
                         List<AuthorSearchDto> authors, SharePointSearchDto sharePoint) {
        this.title = title;
        this.publisher = publisher;
        this.authors = authors;
        this.sharePoint = sharePoint;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PublisherSearchDto getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherSearchDto publisher) {
        this.publisher = publisher;
    }

    public List<AuthorSearchDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorSearchDto> authors) {
        this.authors = authors;
    }

    public SharePointSearchDto getSharePoint() {
        return sharePoint; // TODO ZROBIC ZEBY DZIALALO BO SMERGLA DOSTAJE
    }

    public void setSharePoint(SharePointSearchDto sharePoint) {
        this.sharePoint = sharePoint;
    }
}
