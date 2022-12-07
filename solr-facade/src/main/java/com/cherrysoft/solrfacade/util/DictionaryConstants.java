package com.cherrysoft.solrfacade.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class DictionaryConstants {
  public static final Map<String, String> AVAILABLE_DICTIONARIES = new HashMap<>();
  public static final DictionaryDefinition DICTIONARY_SPANISH = new DictionaryDefinition("es", "spellcheck_es");
  public static final DictionaryDefinition DICTIONARY_ENGLISH = new DictionaryDefinition("en", "spellcheck_en");

  static {
    AVAILABLE_DICTIONARIES.put(DICTIONARY_ENGLISH.alias, DICTIONARY_SPANISH.name);
    AVAILABLE_DICTIONARIES.put(DICTIONARY_ENGLISH.alias, DICTIONARY_ENGLISH.name);
  }

  @Data
  public static class DictionaryDefinition {
    private final String alias;
    private final String name;
  }

}
