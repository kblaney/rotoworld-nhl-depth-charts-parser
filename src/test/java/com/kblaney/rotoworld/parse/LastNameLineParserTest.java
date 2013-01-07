package com.kblaney.rotoworld.parse;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class LastNameLineParserTest
{
  private LastNameLineParser parser;

  @Before
  public void setUp()
  {
    parser = new LastNameLineParser();
  }

  @Test
  public void getLastName_noDigit()
  {
    assertIllegalArgumentException(". Wayne Gretzky");
  }

  private void assertIllegalArgumentException(final String line)
  {
    try
    {
      parser.getLastName(line);
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      assertTrue(e.getMessage().contains(line));
    }
  }

  @Test
  public void getLastName_noDot()
  {
    assertIllegalArgumentException("1 Wayne Gretzky");
  }

  @Test
  public void getLastName_noSpaceAfterDot()
  {
    assertIllegalArgumentException("1.Wayne Gretzky");
  }

  @Test
  public void getLastName_noSpaceAfterFirstName()
  {
    assertIllegalArgumentException("1.Wayne");
  }

  @Test
  public void getLastName_noFirstName()
  {
    assertIllegalArgumentException("1.");
  }

  @Test
  public void getLastName_noPositionShortform()
  {
    assertEquals("Gretzky", parser.getLastName("1. Wayne Gretzky"));
  }

  @Test
  public void getLastName_spaceAtEndOfLine()
  {
    assertEquals("Gretzky", parser.getLastName("1. Wayne Gretzky "));
  }

  @Test
  public void getLastName_positionShortform()
  {
    assertEquals("Gretzky", parser.getLastName("C 1. Wayne Gretzky"));
  }

  @Test
  public void getLastName_multipleDigits()
  {
    assertEquals("Gretzky", parser.getLastName("C 10. Wayne Gretzky"));
  }

  @Test
  public void getLastName_tabAfterDot()
  {
    assertEquals("Gretzky", parser.getLastName("C 1.\tWayne Gretzky"));
  }

  @Test
  public void getLastName_multipleSpacesAfterDot()
  {
    assertEquals("Gretzky", parser.getLastName("C 1.   Wayne Gretzky"));
  }

  @Test
  public void getLastName_spacesBeforeDigit()
  {
    assertEquals("Gretzky", parser.getLastName("   1.   Wayne Gretzky"));
  }

  @Test
  public void getLastName_singleLetterFirstName()
  {
    assertEquals("Gretzky", parser.getLastName("   1.   W Gretzky"));
  }

  @Test
  public void getLastName_multipleLastNames()
  {
    assertEquals("Van Der Gulik", parser.getLastName("1. David Van Der Gulik"));
  }
}
