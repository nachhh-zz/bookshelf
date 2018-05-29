package bookshelf.resources;

public class BookshelfException extends Throwable {
  private int code;
  public BookshelfException() {
      this(500);
  }
  public BookshelfException(int code) {
      this(code, "Error while processing the request", null);
  }
  public BookshelfException(int code, String message) {
      this(code, message, null);
  }
  public BookshelfException(int code, String message, Throwable throwable) {
    super(message, null);
      this.code = code;
  }
  public int getCode() {
      return code;
  }
}
