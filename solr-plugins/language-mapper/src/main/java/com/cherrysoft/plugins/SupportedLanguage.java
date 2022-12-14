package com.cherrysoft.plugins;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Getter
@RequiredArgsConstructor
public enum SupportedLanguage {
  SPANISH("es", "Spanish"),
  ENGLISH("en", "English");

  private final String langCode;
  private final String langName;

  public static boolean isSupportedLang(String langName) {
    return Stream.of(SupportedLanguage.values())
        .anyMatch(supportedLanguage -> supportedLanguage.langName.contains(langName));
  }

  public static boolean isSupportedLangFromCode(String langCode) {
    return Stream.of(SupportedLanguage.values())
        .anyMatch(
            supportedLanguage -> supportedLanguage.canBeIdentifiedWithCode(langCode)
        );
  }

  public static String getSupportedLangNameFromCode(String langCode) {
    return Stream.of(SupportedLanguage.values())
        .filter(supportedLanguage -> supportedLanguage.canBeIdentifiedWithCode(langCode))
        .findAny()
        .map(SupportedLanguage::getLangName)
        .orElseThrow();
  }

  public boolean canBeIdentifiedWithCode(String code) {
    if (isNull(code)) {
      return false;
    }
    return code.contains(this.langCode);
  }

}
