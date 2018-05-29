package bookshelf.resources;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import bookshelf.api.Book;
import bookshelf.db.BooksMapper;
import bookshelf.db.BooksSearchMapper;
import bookshelf.db.DSLContextnManager;

import static bookshelf.core.Tables.*;

/**
 * @author juanbarsola
 * Resource to CRUD books
 * TODO 
 * 1) add endpoint to add books. It should also generate entries in the 
 * inverted index table (book_terms) and the terms table.
 * So we load the add endpoint a bit but we gain performance in the search, which
 * generally has more frequency than adding in a typical scenario.
 * 2) move query stuff to its own module, so we have a better separation of concerns
 */
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
  Logger log = LoggerFactory.getLogger(BookResource.class);
  
  public BookResource() {
    //TODO
  }
  
  @GET
  @Path("/{isbn}")
  @Timed
  /**
   * Get book by isbn
   * @param title
   * @return
   * @throws BookshelfException
   */
  public List<Book> bookByIsbn(@PathParam("isbn") String isbn) throws BookshelfException {
    try {
      List<Book> books = DSLContextnManager.getDSLContext().select().from(BOOKS).where(
          BOOKS.ISBN.eq(isbn)).fetch().map(new BooksMapper());
      return books;
    } catch (Exception e) {
      log.error("There was an error while getting books: ", e);
      throw new BookshelfException(404, "Book with mentioned isbn NOT found", e);
    }
  }
  
  @GET
  @Timed
  /**
   * Search books that contain parts of keyword in their title/description 
   * @param keyword : String containing phrase or term to search for books by title/desc
   * @return
   * @throws BookshelfException
   */
  public List<Book> books(@DefaultValue("") @QueryParam("keyword") Optional<String> keyword) throws BookshelfException {
    try {
      if(keyword.get().isEmpty() || !keyword.isPresent()) {
        return DSLContextnManager.getDSLContext().select().from(BOOKS).fetch().map(new BooksMapper());
      }
      // extract search terms separated by space
      String[] terms = keyword.get().trim().split(" ");
      List<String> cleanedTerms = Arrays.asList(terms);
      // lower terms and any other cleansing 
      //TODO filter out "wide" terms like pronouns, etc
      cleanedTerms.stream().map(term -> term.toLowerCase());
      List<Book> books = DSLContextnManager.getDSLContext().select().from(
          TERMS_BOOKS.innerJoin(BOOKS).on(TERMS_BOOKS.BOOK_ID.eq(BOOKS.ISBN))
          .innerJoin(TERMS).on(TERMS_BOOKS.TERM_ID.eq(TERMS.ID))
      ).where(TERMS.TERM.in(cleanedTerms))
      .fetch().map(new BooksSearchMapper());
      
      return books;
    } catch (Exception e) {
      log.error("There was an error while getting books: ", e);
      throw new BookshelfException(404, "Book with mentioned isbn NOT found", e);
    }
  }
}
