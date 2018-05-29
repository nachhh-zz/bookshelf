package bookshelf.db;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manage access to underlying DB 
 * @author juanbarsola
 *
 */
public class DSLContextnManager {
  
  private static DSLContext dslCtx;
  //private static String url = System.getProperty("jdbc.url");
  private static String url = "jdbc:sqlite:bookshelf.db";
  //private static String driver = System.getProperty("jdbc.driver");
  private static String driver = "org.sqlite.JDBC";
  
  /**
   * @return instance of jppq DSLContext so we can communicate with the underlying DB
   * @throws SQLException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ClassNotFoundException
   */
  public static DSLContext getDSLContext() 
      throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    if (dslCtx == null) {
      // load sqlite JDBC driver using current class loader
      Class.forName(driver).newInstance();
      Connection conn = DriverManager.getConnection(url);
      dslCtx = DSL.using(new DefaultConfiguration()
          .set(conn)
          .set(SQLDialect.SQLITE)
      );
      return dslCtx;
    }
    return dslCtx;
  }
}
