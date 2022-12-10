package com.cherrysoft.solrfacade.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum SupportedDictionary {
  SPANISH("es", "spellcheck_es"),
  ENGLISH("en", "spellcheck_en");

  private final String alias;
  private final String name;

  public static String getNameOf(String dictionaryAlias) {
    return Stream.of(SupportedDictionary.values())
        .filter(d -> d.alias.equals(dictionaryAlias))
        .findFirst()
        .orElse(SupportedDictionary.SPANISH).name;
  }

}
