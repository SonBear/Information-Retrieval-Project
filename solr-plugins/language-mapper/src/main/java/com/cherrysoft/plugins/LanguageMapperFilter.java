package com.cherrysoft.plugins;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

public class LanguageMapperFilter extends TokenFilter {
  private final CharTermAttribute termAttribute = addAttribute(CharTermAttribute.class);

  protected LanguageMapperFilter(TokenStream input) {
    super(input);
  }

  @Override
  public boolean incrementToken() throws IOException {
    if (!input.incrementToken()) {
      return false;
    }
    char[] buffer = termAttribute.buffer();
    int length = termAttribute.length();
    String token = new String(buffer, 0, length);
    String resultToken;
    if (SupportedLanguage.isSupportedLang(token)) {
      resultToken = token;
    } else if (SupportedLanguage.isSupportedLangFromCode(token)) {
      resultToken = SupportedLanguage.getSupportedLangNameFromCode(token);
    } else {
      resultToken = token;
    }
    termAttribute.setEmpty().append(resultToken);
    return true;
  }

}
