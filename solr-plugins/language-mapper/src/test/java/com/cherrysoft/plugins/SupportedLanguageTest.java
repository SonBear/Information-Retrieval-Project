package com.cherrysoft.plugins;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SupportedLanguageTest {

  @ParameterizedTest
  @CsvSource({"Spanish,true", "English,true", "French,false"})
  void givenLangs_thenIsSupportedLangShouldMatchTheExpectedInput(String langName, boolean expected) {
    assertEquals(SupportedLanguage.isSupportedLang(langName), expected);
  }

  @ParameterizedTest
  @CsvSource({"es_MX,true", "es_ES,true", "en_US,true", "en,true", "fr,false"})
  void givenCodeLangCodes_thenIsSupportedLangFromCodeShouldMatchThenExpectedInput(String langCode, boolean expected) {
    assertEquals(SupportedLanguage.isSupportedLangFromCode(langCode), expected);
  }

  @ParameterizedTest
  @CsvSource({"es,Spanish", "es_MX,Spanish", "en_US,English", "en,English"})
  void givenLangCodes_thenGetSupportedLangNameFromCodeShouldReturnExpectedLangName(String langCode, String expectedLangName) {
    assertEquals(SupportedLanguage.getSupportedLangNameFromCode(langCode), expectedLangName);
  }

  @ParameterizedTest
  @ValueSource(strings = {"fr", "pt", "zh"})
  void givenInvalidLangCode_thenShouldThrowAnException(String langCode) {
    assertThrows(NoSuchElementException.class, () -> {
      SupportedLanguage.getSupportedLangNameFromCode(langCode);
    });
  }

}
