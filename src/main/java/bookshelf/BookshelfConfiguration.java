package bookshelf;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.bundles.assets.AssetsBundleConfiguration;
import io.dropwizard.bundles.assets.AssetsConfiguration;

public class BookshelfConfiguration extends Configuration implements AssetsBundleConfiguration{
  
  @Valid
  @NotNull
  @JsonProperty
  private final AssetsConfiguration assets = AssetsConfiguration.builder().build();
  
  public AssetsConfiguration getAssetsConfiguration() {
    return assets;
  }
}
