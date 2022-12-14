package com.cherrysoft.plugins;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

import java.util.Map;

public class LanguageMapperFilterFactory extends TokenFilterFactory {

  public LanguageMapperFilterFactory(Map<String, String> args) {
    super(args);
  }

  @Override
  public TokenStream create(TokenStream tokenStream) {
    return new LanguageMapperFilter(tokenStream);
  }

}
