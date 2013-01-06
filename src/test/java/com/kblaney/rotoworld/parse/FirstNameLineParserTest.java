package com.kblaney.rotoworld.parse;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class FirstNameLineParserTest
{
  private FirstNameLineParser parser;

  @Before
  public void setUp()
  {
    parser = new FirstNameLineParser();
  }

  @Test
  public void getFirstName_noDigit()
  {
    assertIllegalArgumentException(". Wayne Gretzky");
  }

  private void assertIllegalArgumentException(final String line)
  {
    try
    {
      parser.getFirstName(line);
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      assertTrue(e.getMessage().contains(line));
    }
  }

  @Test
  public void getFirstName_noDot()
  {
    assertIllegalArgumentException("1 Wayne Gretzky");
  }

  @Test
  public void getFirstName_noSpaceAfterDot()
  {
    assertIllegalArgumentException("1.Wayne Gretzky");
  }

  @Test
  public void getFirstName_noFirstName()
  {
    assertIllegalArgumentException("1.");
  }

  @Test
  public void getFirstName_noPositionShortform()
  {
    assertEquals("Wayne", parser.getFirstName("1. Wayne Gretzky"));
  }

  @Test
  public void getFirstName_positionShortform()
  {
    assertEquals("Wayne", parser.getFirstName("C 1. Wayne Gretzky"));
  }

  @Test
  public void getFirstName_multipleDigits()
  {
    assertEquals("Wayne", parser.getFirstName("C 10. Wayne Gretzky"));
  }

  @Test
  public void getFirstName_tabAfterDot()
  {
    assertEquals("Wayne", parser.getFirstName("C 1.\tWayne Gretzky"));
  }

  @Test
  public void getFirstName_multipleSpacesAfterDot()
  {
    assertEquals("Wayne", parser.getFirstName("C 1.   Wayne Gretzky"));
  }

  @Test
  public void getFirstName_spacesBeforeDigit()
  {
    assertEquals("Wayne", parser.getFirstName("   1.   Wayne Gretzky"));
  }

  @Test
  public void getFirstName_singleLetterFirstName()
  {
    assertEquals("W", parser.getFirstName("   1.   W Gretzky"));
  }
}
