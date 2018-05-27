package bookshelf.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author juanbarsola
 * This POJO represents a book object
 */
public class Book {
  private String isbn;
  private String title;
  private String description;
  
  public Book() {
    // Jackson deserialization
  }
  
  public Book(String isbn, String title, String description) {
    super();
    this.isbn = isbn;
    this.title = title;
    this.description = description;
  }
  
  @JsonProperty
  public String getIsbn() {
    return isbn;
  }
  @JsonProperty
  public String getTitle() {
    return title;
  }
  @JsonProperty
  public String getDescription() {
    return description;
  }
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public void setDescription(String description) {
    this.description = description;
  }
}
