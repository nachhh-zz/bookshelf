package bookshelf;

import bookshelf.resources.BookResource;
import bookshelf.resources.BookshelfExceptionMapper;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;

/**
 * Here we configure application wide settings 
 * @author juanbarsola
 */
public class BookshelfApplication extends Application<BookshelfConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BookshelfApplication().run(args);
    }

    @Override
    public String getName() {
        return "bookshelf";
    }

    @Override
    public void initialize(final Bootstrap<BookshelfConfiguration> bootstrap) {
      // make angular frontend app available in / and define index.html as default page
      bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/bookshelf/dist/bookshelf/", "/", "index.html"));
    }

    @Override
    public void run(final BookshelfConfiguration configuration,
                    final Environment environment) {
      final BookResource resource = new BookResource();
      // register endpoints
      environment.jersey().register(resource);
      // register exception mapping
      environment.jersey().register(new BookshelfExceptionMapper());
    }
}
