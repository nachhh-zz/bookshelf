package bookshelf.db;

import org.jooq.Record;
import org.jooq.RecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bookshelf.api.Book;
import bookshelf.core.tables.records.BooksRecord;


/**
 * This mapper is used when we search for books by kewords in title/desc
 * so we end up with a joined record and extract the books
 * @author juanbarsola
 *
 */
public class BooksSearchMapper implements RecordMapper<Record, Book> {
  
  Logger log = LoggerFactory.getLogger(BooksMapper.class);
  
  /**
   * @param record : a join of a book and a term, every time term 
   * is part of book title/desc (join done through an inverted index scan)
   */
  @Override
  public Book map(Record record) {
    Book book = new Book();
    book.setIsbn((String)record.get("isbn"));
    book.setTitle((String)record.get("title"));
    book.setDescription((String)record.get("description"));
    return book;
  }
}
