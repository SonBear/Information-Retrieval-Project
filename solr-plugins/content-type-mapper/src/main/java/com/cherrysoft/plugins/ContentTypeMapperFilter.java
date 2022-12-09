package com.cherrysoft.plugins;

import com.cherrysoft.plugins.utils.MimeTypes;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

public class ContentTypeMapperFilter extends TokenFilter {
  private final CharTermAttribute termAttribute = addAttribute(CharTermAttribute.class);

  protected ContentTypeMapperFilter(TokenStream tokenStream) {
    super(tokenStream);
  }

  @Override
  public boolean incrementToken() throws IOException {
    if (!input.incrementToken()) {
      return false;
    }
    char[] buffer = termAttribute.buffer();
    int length = termAttribute.length();
    String token = new String(buffer, 0, length);
    termAttribute.setEmpty().append(MimeTypes.EXTENSIONS.get(token));
    return true;
  }

}
