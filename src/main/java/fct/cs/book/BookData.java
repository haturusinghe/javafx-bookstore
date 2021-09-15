package fct.cs.book;

import fct.cs.Author.Author;

import java.time.Year;

public class BookData {
    private int book_id;
    private String isbn;
    private int category_id;
    private String publisher;
    private int author_id;
    private String title;
    private Year b_year;
    private int mrp;
    private int num_pages;
    private String lang;
    private String book_description;
    private String author;
    private String categoryName;

    public BookData(int book_id, String isbn, int category_id, String publisher, int author_id, String title, int b_year, int mrp, int num_pages, String lang, String book_description, String author, String categoryName) {
        this.book_id = book_id;
        this.isbn = isbn;
        this.category_id = category_id;
        this.publisher = publisher;
        this.author_id = author_id;
        this.title = title;
        this.b_year = Year.of(b_year);
        this.mrp = mrp;
        this.num_pages = num_pages;
        this.lang = lang;
        this.book_description = book_description;
        this.author = author;
        this.categoryName = categoryName;
    }

    public BookData(int book_id, String isbn, int category_id, String publisher, int author_id, String title, short b_year, int mrp, int num_pages, String lang, String book_description) {
        this.book_id = book_id;
        this.isbn = isbn;
        this.category_id = category_id;
        this.publisher = publisher;
        this.author_id = author_id;
        this.title = title;
        this.b_year = Year.of(b_year);
        this.mrp = mrp;
        this.num_pages = num_pages;
        this.lang = lang;
        this.book_description = book_description;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Year getB_year() {
        return b_year;
    }

    public void setB_year(Year b_year) {
        this.b_year = b_year;
    }

    public int getMrp() {
        return mrp;
    }

    public void setMrp(int mrp) {
        this.mrp = mrp;
    }

    public int getNum_pages() {
        return num_pages;
    }

    public void setNum_pages(int num_pages) {
        this.num_pages = num_pages;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getBook_description() {
        return book_description;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", isbn='" + isbn + '\'' +
                ", category_id=" + category_id +
                ", publisher='" + publisher + '\'' +
                ", author_id=" + author_id +
                ", title='" + title + '\'' +
                ", b_year=" + b_year +
                ", mrp=" + mrp +
                ", num_pages=" + num_pages +
                ", lang='" + lang + '\'' +
                ", book_description='" + book_description + '\'' +
                '}';
    }
}
