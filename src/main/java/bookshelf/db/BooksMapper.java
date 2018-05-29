package bookshelf.db;

import org.jooq.Record;
import org.jooq.RecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bookshelf.api.Book;
import bookshelf.core.tables.records.BooksRecord;

  
public class BooksMapper implements RecordMapper<Record, Book> {
  
  Logger log = LoggerFactory.getLogger(BooksMapper.class);
  
  @Override
  public Book map(Record record) {
    Book book = new Book();
    BooksRecord booksRecord = (BooksRecord) record;
    book.setIsbn(booksRecord.getIsbn());
    book.setTitle(booksRecord.getTitle());
    book.setDescription(booksRecord.getDescription());
    return book;
  }
}
