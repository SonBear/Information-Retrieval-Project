package com.cherrysoft.solrfacade.util;

public class TextUtils {

  public static String cleanText(String text) {
    String withoutBreakLines = removeBreakLines(text);
    String trimmedText = withoutBreakLines.trim();
    return normalizeSpaces(trimmedText);
  }

  private static String removeBreakLines(String text) {
    return text
        .replace("\n", "")
        .replace("\r", "")
        .replace("\t", "");
  }

  private static String normalizeSpaces(String text) {
    return text.replaceAll(" +", " ");
  }

}
