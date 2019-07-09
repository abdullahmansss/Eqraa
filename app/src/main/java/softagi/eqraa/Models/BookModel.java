package softagi.eqraa.Models;

public class BookModel
{
    private String title,author,publisher,published_date,desc,thumbnail;
    int page_count;
    double rate;

    public BookModel() {
    }

    public BookModel(String title, String author, String publisher, String published_date, String desc, String thumbnail, int page_count, double rate) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.published_date = published_date;
        this.desc = desc;
        this.thumbnail = thumbnail;
        this.page_count = page_count;
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
