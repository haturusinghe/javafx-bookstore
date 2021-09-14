package fct.cs.NewBook;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookData {
    private final StringProperty isbn = new SimpleStringProperty("");
    private final StringProperty publisher = new SimpleStringProperty("");
   // private final StringProperty title = new SimpleStringProperty("");
    private final StringProperty language = new SimpleStringProperty("");
    private final StringProperty Description = new SimpleStringProperty("");
   // private final StringProperty book_year = new SimpleStringProperty("");
    private final IntegerProperty book_id = new SimpleIntegerProperty(0);
    //private final IntegerProperty category = new SimpleIntegerProperty(0);
  /*  private final IntegerProperty author_id = new SimpleIntegerProperty(0);
    private final IntegerProperty mrp = new SimpleIntegerProperty(0);
    private final IntegerProperty numOf_pages = new SimpleIntegerProperty(0);*/


    public BookData(String isbn,String publisher,String title,String language,String Description,String book_year,int book_id,int category) {
        setIsbn(isbn);
        setPublisher(publisher);
      //  setTitle(title);
        setLanguage(language);
        setDescription(Description);
       // setBook_year(book_year);
        setBook_id(book_id);
        //setCategory(category);
    }


    public String getIsbn() {
        return isbn.get();
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public String getPublisher() {
        return publisher.get();
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    /*public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }*/

    public String getLanguage() {
        return language.get();
    }

    public StringProperty languageProperty() {
        return language;
    }

    public void setLanguage(String language) {
        this.language.set(language);
    }

    public String getDescription() {
        return Description.get();
    }

    public StringProperty descriptionProperty() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description.set(description);
    }

   /* public String getBook_year() {
        return book_year.get();
    }

    public StringProperty book_yearProperty() {
        return book_year;
    }

    public void setBook_year(String book_year) {
        this.book_year.set(book_year);
    }*/

    public String getBook_id() {
        return book_id.get();
    }

    public IntegerProperty book_idProperty() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id.set(book_id);
    }

  /*  public int getCategory() {
        return category.get();
    }

    public IntegerProperty categoryProperty() {
        return category;
    }

    public void setCategory(int category) {
        this.category.set(category);
    }
*/



}
