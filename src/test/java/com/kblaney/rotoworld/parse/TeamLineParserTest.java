package com.kblaney.rotoworld.parse;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class TeamLineParserTest
{
  private TeamLineParser parser;

  @Before
  public void setUp()
  {
    parser = new TeamLineParser();
  }

  @Test
  public void doesLineHaveTeamShortform_emptyLine()
  {
    assertFalse(parser.doesLineHaveTeamShortform(""));
  }

  @Test
  public void doesLineHaveTeamShortform_singleCharLine()
  {
    assertFalse(parser.doesLineHaveTeamShortform("A"));
  }

  @Test
  public void doesLineHaveTeamShortform_noTeamShortform()
  {
    assertFalse(parser.doesLineHaveTeamShortform("12##23"));
  }

  @Test
  public void doesLineHaveTeamShortform_teamShortformNotAtStart()
  {
    assertFalse(parser.doesLineHaveTeamShortform("1ANA"));
  }

  @Test
  public void doesLineHaveTeamShortform_teamShortformNotAtEnd()
  {
    assertFalse(parser.doesLineHaveTeamShortform("ANA1"));
  }

  @Test
  public void doesLineHaveTeamShortform_twoCharTeamShortform()
  {
    assertTrue(parser.doesLineHaveTeamShortform("NJ"));
  }

  @Test
  public void doesLineHaveTeamShortform_threeCharTeamShortform()
  {
    assertTrue(parser.doesLineHaveTeamShortform("BOS"));
  }

  @Test
  public void getTeamShortform_emptyLine()
  {
    assertGetTeamShortformFailure("");
  }

  private void assertGetTeamShortformFailure(final String line)
  {
    try
    {
      parser.getTeamShortform(line);
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      assertTrue(e.getMessage().contains(line));
    }
  }

  @Test
  public void getTeamShortform_singleCharLine()
  {
    assertGetTeamShortformFailure("X");
  }

  @Test
  public void getTeamShortform_noTeamShortform()
  {
    assertGetTeamShortformFailure("123");
  }

  @Test
  public void getTeamShortform_teamShortformNotAtStart()
  {
    assertGetTeamShortformFailure("-BUF");
  }

  @Test
  public void getTeamShortform_teamShortformNotAtEnd()
  {
    assertGetTeamShortformFailure("BUF^");
  }

  @Test
  public void getTeamShortform_twoCharTeamShortform()
  {
    assertEquals("LA", parser.getTeamShortform("LA"));
  }

  @Test
  public void getTeamShortform_threeCharTeamShortform()
  {
    assertEquals("NYI", parser.getTeamShortform("NYI"));
  }
}
