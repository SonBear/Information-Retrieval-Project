package com.cherrysoft.plugins.utils;

import java.util.HashMap;
import java.util.Map;

public class MimeTypes {
  public static final Map<String, String> EXTENSIONS = new HashMap<>();

  static {
    EXTENSIONS.put("application/pdf", "PDF");
    EXTENSIONS.put("text/html", "HTML");
    EXTENSIONS.put("text/xml", "XML");
    EXTENSIONS.put("text/plain", "TEXT");

    EXTENSIONS.put("application/msword", "DOC");

    EXTENSIONS.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "DOCX");
    EXTENSIONS.put("application/vnd.openxmlformats-officedocument.wordprocessingml.template", "DOTX");
    EXTENSIONS.put("application/vnd.ms-word.document.macroEnabled.12", "DOCM");
    EXTENSIONS.put("application/vnd.ms-word.template.macroEnabled.12", "DOTM");

    EXTENSIONS.put("application/vnd.ms-excel", "XSL");

    EXTENSIONS.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "XLSX");
    EXTENSIONS.put("application/vnd.openxmlformats-officedocument.spreadsheetml.template", "XLTX");
    EXTENSIONS.put("application/vnd.ms-excel.sheet.macroEnabled.12", "XLSM");
    EXTENSIONS.put("application/vnd.ms-excel.template.macroEnabled.12", "XLTM");
    EXTENSIONS.put("application/vnd.ms-excel.addin.macroEnabled.12", "XLAM");
    EXTENSIONS.put("application/vnd.ms-excel.sheet.binary.macroEnabled.12", "XLSB");

    EXTENSIONS.put("application/vnd.ms-powerpoint", "PPT");

    EXTENSIONS.put("application/vnd.openxmlformats-officedocument.presentationml.presentation", "PPTX");
    EXTENSIONS.put("application/vnd.openxmlformats-officedocument.presentationml.template", "POTX");
    EXTENSIONS.put("application/vnd.openxmlformats-officedocument.presentationml.slideshow", "PPSX");
    EXTENSIONS.put("application/vnd.ms-powerpoint.addin.macroEnabled.12", "PPAM");
    EXTENSIONS.put("application/vnd.ms-powerpoint.presentation.macroEnabled.12", "PPTM");
    EXTENSIONS.put("application/vnd.ms-powerpoint.template.macroEnabled.12", "POTM");
    EXTENSIONS.put("application/vnd.ms-powerpoint.slideshow.macroEnabled.12", "PPSM");

    EXTENSIONS.put("application/vnd.ms-access", "MDB");
  }

}
