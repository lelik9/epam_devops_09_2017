package com.epam.Philippov;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Task8Test {

  @Test
  void validPolindrom() {
    Task8 polindrom = new Task8();
    assertTrue(polindrom.isPolindrom("121"));
    assertTrue(polindrom.isPolindrom("1221"));
  }

  @Test
  void inValidPolindrom() {
    Task8 polindrom = new Task8();
    assertTrue(polindrom.isPolindrom("1231"));
    assertTrue(polindrom.isPolindrom("abc"));
  }
}