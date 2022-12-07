package com.cherrysoft.crawler.repository.imp;

import com.cherrysoft.crawler.model.WebPageUrlSet;
import com.cherrysoft.crawler.repository.URLRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.HashSet;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class URLRepositoryImp implements URLRepository {
  public static final String URLS_FILE_NAME = "urls.json";
  private final Gson gson;

  @Override
  public void mergeAndSaveUrls(WebPageUrlSet urlList) {
    WebPageUrlSet savedUrls = getSavedUrls();
    WebPageUrlSet withNewUrls = savedUrls.mergedWith(urlList);
    saveUrls(withNewUrls);
  }

  private void saveUrls(WebPageUrlSet urlSet) {
    try (Writer writer = new FileWriter(URLS_FILE_NAME)) {
      gson.toJson(urlSet.getUrls(), writer);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Override
  public WebPageUrlSet getUnsavedUrls(WebPageUrlSet urlSet) {
    WebPageUrlSet savedUrls = getSavedUrls();
    return urlSet.asymmetricDiff(savedUrls);
  }

  @Override
  public WebPageUrlSet getSavedUrls() {
    try {
      return tryGetSavedUrlSet();
    } catch (IOException e) {
      e.printStackTrace();
      return WebPageUrlSet.EMPTY;
    }
  }

  private WebPageUrlSet tryGetSavedUrlSet() throws IOException {
    try (Reader reader = new FileReader(URLS_FILE_NAME)) {
      List<String> savedUrls = gson.fromJson(reader, new TypeToken<>() {
      }.getType());
      return new WebPageUrlSet(new HashSet<>(savedUrls));
    }
  }

}
