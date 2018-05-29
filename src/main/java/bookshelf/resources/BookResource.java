package bookshelf.resources;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

import org.jooq.Record;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import bookshelf.api.Book;
import bookshelf.db.BooksMapper;
import bookshelf.db.DSLContextnManager;

import static bookshelf.core.Tables.*;

/**
 * @author juanbarsola
 * Resource to CRUD books
 */
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
  Logger log = LoggerFactory.getLogger(BookResource.class);
  
  public BookResource() {
    //TODO
  }

  @GET
  @Timed
  public List<Book> books(@QueryParam("title") Optional<String> title) throws BookshelfException {
    try {
      List<Book> books = DSLContextnManager.getDSLContext().select().from(BOOKS).fetch().map(new BooksMapper());
      return books;
    } catch (Exception e) {
      log.error("There was an error while getting books: ", e);
      throw new BookshelfException(404, "Book with mentioned isbn NOT found", e);
    }
  }
}
