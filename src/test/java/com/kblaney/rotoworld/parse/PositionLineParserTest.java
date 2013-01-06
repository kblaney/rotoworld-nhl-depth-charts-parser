package com.kblaney.rotoworld.parse;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class PositionLineParserTest
{
  private String lineWithCenterPosition;
  private String lineWithLeftWingPosition;
  private String lineWithRightWingPosition;
  private String lineWithDefencemanPosition;
  private String lineWithGoaliePosition;
  private PositionLineParser parser;

  @Before
  public void setUp()
  {
    lineWithCenterPosition = "C 1. Ryan Getzlaf";
    lineWithLeftWingPosition = "LW 1. Alexander Mogilny";
    lineWithRightWingPosition = "RW 1. Ilya Kovalchuk";
    lineWithDefencemanPosition = "D 1. Zdeno Chara";
    lineWithGoaliePosition = "G 1. Tim Thomas";
    parser = new PositionLineParser();
  }

  @Test
  public void doesLineHavePosition_noPositionShortform()
  {
    assertFalse(parser.doesLineHavePosition("Kyle"));
  }

  @Test
  public void doesLineHavePosition_positionShortformNotAtStart()
  {
    assertFalse(parser.doesLineHavePosition("AD"));
  }

  @Test
  public void doesLineHavePosition_center()
  {
    assertTrue(parser.doesLineHavePosition(lineWithCenterPosition));
  }

  @Test
  public void doesLineHavePosition_leftWing()
  {
    assertTrue(parser.doesLineHavePosition(lineWithLeftWingPosition));
  }

  @Test
  public void doesLineHavePosition_rightWing()
  {
    assertTrue(parser.doesLineHavePosition(lineWithRightWingPosition));
  }

  @Test
  public void doesLineHavePosition_defenceman()
  {
    assertTrue(parser.doesLineHavePosition(lineWithDefencemanPosition));
  }

  @Test
  public void doesLineHavePosition_goalie()
  {
    assertTrue(parser.doesLineHavePosition(lineWithGoaliePosition));
  }

  @Test
  public void getPositionShortform_center()
  {
    assertEquals("F", parser.getPositionShortform(lineWithCenterPosition));
  }

  @Test
  public void getPositionShortform_leftWing()
  {
    assertEquals("F", parser.getPositionShortform(lineWithLeftWingPosition));
  }

  @Test
  public void getPositionShortform_rightWing()
  {
    assertEquals("F", parser.getPositionShortform(lineWithRightWingPosition));
  }

  @Test
  public void getPositionShortform_defenceman()
  {
    assertEquals("D", parser.getPositionShortform(lineWithDefencemanPosition));
  }

  @Test
  public void getPositionShortform_goalie()
  {
    assertEquals("G", parser.getPositionShortform(lineWithGoaliePosition));
  }

  @Test
  public void getPositionShortform_noPositionShortform()
  {
    final String line = "Kyle";
    try
    {
      parser.getPositionShortform(line);
      fail();
    }
    catch (final IllegalArgumentException e)
    {
      assertTrue(e.getMessage().contains(line));
    }
  }
}
