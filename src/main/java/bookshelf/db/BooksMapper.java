package bookshelf.db;

import org.jooq.Record;
import org.jooq.RecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bookshelf.api.Book;
import bookshelf.core.tables.records.BooksRecord;

/**
 * Map simple book records to a Book pojo. Can be used for 
 * search by isbn or by dates, etc (No join involved)
 * @author juanbarsola
 *
 */
public class BooksMapper implements RecordMapper<Record, Book> {
  
  Logger log = LoggerFactory.getLogger(BooksMapper.class);
  
  @Override
  public Book map(Record record) {
    Book book = new Book();
    BooksRecord booksRecord = (BooksRecord) record;
    //TODO get all fields
    book.setIsbn(booksRecord.getIsbn());
    book.setTitle(booksRecord.getTitle());
    book.setDescription(booksRecord.getDescription());
    return book;
  }
}
