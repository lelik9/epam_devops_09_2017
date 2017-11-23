package com.epam.Philippov;

public class Task8 {

  public boolean isPolindrom(String string) {
    if (isNumeric(string)) {
      int strLength = string.length();

      if(strLength == 1) {
        return true;
      }

      for (int i = 0; i < strLength % 2; i++) {
        if (string.charAt(i) != string.charAt(strLength - 1 - i)) {
          break;
        }
      }
      return true;
    }
    return false;
  }

  private static boolean isNumeric(String str) {
    try {
      double d = Double.parseDouble(str);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }
}
