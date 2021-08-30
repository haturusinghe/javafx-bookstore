package fct.cs.Books;

import java.time.Year;

public class Book {
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

    public Book(int book_id, String isbn, int category_id, String publisher, int author_id, String title, short b_year, int mrp, int num_pages, String lang, String book_description) {
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
