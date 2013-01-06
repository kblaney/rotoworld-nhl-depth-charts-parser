package com.kblaney.rotoworld.parse;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class PlayerTest
{
  private String firstName;
  private String lastName;
  private String teamShortform;
  private String positionShortform;
  private Player player;

  @Before
  public void setUp()
  {
    firstName = "FIRST_NAME";
    lastName = "LAST_NAME";
    teamShortform = "BOS";
    positionShortform = "G";
    player = new Player(firstName, lastName, teamShortform, positionShortform);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullFirstName()
  {
    new Player(null, lastName, teamShortform, positionShortform);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullLastName()
  {
    new Player(firstName, null, teamShortform, positionShortform);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullTeamShortform()
  {
    new Player(firstName, lastName, null, positionShortform);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullPositionShortform()
  {
    new Player(firstName, lastName, teamShortform, null);
  }

  @Test
  public void getFirstName()
  {
    assertEquals(firstName, player.getFirstName());
  }

  @Test
  public void getLastName()
  {
    assertEquals(lastName, player.getLastName());
  }

  @Test
  public void getTeamShortform()
  {
    assertEquals(teamShortform, player.getTeamShortform());
  }

  @Test
  public void getPositionShortform()
  {
    assertEquals(positionShortform, player.getPositionShortform());
  }

  @Test
  public void equals_null()
  {
    assertFalse(player.equals(null));
  }

  @Test
  public void equals_wrongClass()
  {
    assertFalse(player.equals("ARBITRARY_STRING"));
  }

  @Test
  public void equals_differentFirstName()
  {
    final String differentFirstName = firstName + "A";
    final Player differentPlayer = new Player(differentFirstName, lastName, teamShortform, positionShortform);
    assertFalse(player.equals(differentPlayer));
  }

  @Test
  public void equals_differentLastName()
  {
    final String differentLastName = lastName + "A";
    final Player differentPlayer = new Player(firstName, differentLastName, teamShortform, positionShortform);
    assertFalse(player.equals(differentPlayer));
  }

  @Test
  public void equals_differentTeamShortform()
  {
    final String differentTeamShortform = teamShortform + "A";
    final Player differentPlayer = new Player(firstName, lastName, differentTeamShortform, positionShortform);
    assertFalse(player.equals(differentPlayer));
  }

  @Test
  public void equals_differentPositionShortform()
  {
    final String differentPositionShortform = positionShortform + "A";
    final Player differentPlayer = new Player(firstName, lastName, teamShortform, differentPositionShortform);
    assertFalse(player.equals(differentPlayer));
  }

  @Test
  public void equals_sameInstance()
  {
    assertTrue(player.equals(player));
  }

  @Test
  public void equals_equalPlayer()
  {
    final Player equalPlayer = new Player(firstName, lastName, teamShortform, positionShortform);
    assertTrue(player.equals(equalPlayer));
  }

  @Test
  public void hashCode_sameInstance()
  {
    assertEquals(player.hashCode(), player.hashCode());
  }

  @Test
  public void hashCode_equalPlayer()
  {
    final Player equalPlayer = new Player(firstName, lastName, teamShortform, positionShortform);
    assertEquals(player.hashCode(), equalPlayer.hashCode());
  }

  @Test
  public void testToString()
  {
    assertEquals("Wayne Gretzky:F:EDM", new Player("Wayne", "Gretzky", "EDM", "F").toString());
  }
}
