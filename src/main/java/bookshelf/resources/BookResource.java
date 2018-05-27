package bookshelf.resources;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

import com.codahale.metrics.annotation.Timed;

import bookshelf.api.Book;

/**
 * @author juanbarsola
 * Resource to CRUD books
 */
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
  public BookResource() {
    //TODO
  }

  @GET
  @Timed
  public List<Book> books(@QueryParam("title") Optional<String> title) {
    List<Book> books = new ArrayList<>();
    books.add(new Book("isbn-123", "Ethics", "Ethics according to Aristole"));
    return books;
  }
}
