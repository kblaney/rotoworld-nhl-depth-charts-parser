package com.kblaney.rotoworld.parse;

import java.io.StringReader;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.Reader;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public final class PlayersSupplierTest
{
  private String endOfInputIndicator;
  private BufferedReader bufferedReader;

  @Before
  public void setUp()
  {
    endOfInputIndicator = null;
    bufferedReader = mock(BufferedReader.class);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullReader()
  {
    new PlayersSupplier((Reader)null);
  }

  @Test(expected = IOException.class)
  public void get_readFailure() throws Exception
  {
    when(bufferedReader.readLine()).thenThrow(new IOException());
    new PlayersSupplier(bufferedReader).get();
    verify(bufferedReader).close();
  }

  @Test(expected = IllegalArgumentException.class)
  public void get_noTeamShortform() throws Exception
  {
    when(bufferedReader.readLine()).thenReturn("C 1. Ryan Getzlaf",
          endOfInputIndicator);
    new PlayersSupplier(bufferedReader).get();
    verify(bufferedReader).close();
  }

  @Test(expected = IllegalArgumentException.class)
  public void get_noPositionShortform() throws Exception
  {
    when(bufferedReader.readLine()).thenReturn("ANA", " 2. Ryan Getzlaf",
          endOfInputIndicator);
    new PlayersSupplier(bufferedReader).get();
    verify(bufferedReader).close();
  }

  @Test
  public void get_emptyStringReader() throws Exception
  {
    assertTrue(new PlayersSupplier(new StringReader("")).get().isEmpty());
  }

  @Test
  public void get_emptyBufferedReader() throws Exception
  {
    when(bufferedReader.readLine()).thenReturn(endOfInputIndicator);
    assertTrue(new PlayersSupplier(bufferedReader).get().isEmpty());
    verify(bufferedReader).close();
  }

  @Test
  public void get_onePlayer() throws Exception
  {
    when(bufferedReader.readLine()).thenReturn("ANA", "C 1. Ryan Getzlaf",
          endOfInputIndicator);
    assertEquals(Lists.newArrayList(new Player("Ryan", "Getzlaf", "ANA", "F")),
          new PlayersSupplier(bufferedReader).get());
    verify(bufferedReader).close();
  }

  @Test
  public void get_oneTeam() throws Exception
  {
    when(bufferedReader.readLine()).thenReturn(
          "ANA",
          "C 1. Ryan Getzlaf",
          "  2. Saku Koivu",
          "  3. Kyle Chipchura",
          "LW 1. Bobby Ryan",
          "   2. Jason Blake",
          "   3. Todd Marchant",
          "RW 1. Corey Perry",
          "   2. Teemu Selanne",
          "   3. Dan Sexton",
          "D  1. Lubomir Visnovsky",
          "   2. Toni Lydman",
          "   3. Luca Sbisa",
          "   4. Cam Fowler",
          "G  1. Jonas Hiller",
          "   2. Curtis McElhinney",
          endOfInputIndicator);
    assertEquals(Lists.newArrayList(
          new Player("Ryan", "Getzlaf", "ANA", "F"),
          new Player("Saku", "Koivu", "ANA", "F"),
          new Player("Kyle", "Chipchura", "ANA", "F"),
          new Player("Bobby", "Ryan", "ANA", "F"),
          new Player("Jason", "Blake", "ANA", "F"),
          new Player("Todd", "Marchant", "ANA", "F"),
          new Player("Corey", "Perry", "ANA", "F"),
          new Player("Teemu", "Selanne", "ANA", "F"),
          new Player("Dan", "Sexton", "ANA", "F"),
          new Player("Lubomir", "Visnovsky", "ANA", "D"),
          new Player("Toni", "Lydman", "ANA", "D"),
          new Player("Luca", "Sbisa", "ANA", "D"),
          new Player("Cam", "Fowler", "ANA", "D"),
          new Player("Jonas", "Hiller", "ANA", "G"),
          new Player("Curtis", "McElhinney", "ANA", "G")),
          new PlayersSupplier(bufferedReader).get());
    verify(bufferedReader).close();
  }

  @Test
  public void get_threeTeams() throws Exception
  {
    when(bufferedReader.readLine()).thenReturn(
          "ANA",
          "C 1. Ryan Getzlaf",
          "  2. Saku Koivu",
          "LW 1. Bobby Ryan",
          "   2. Jason Blake",
          "RW 1. Corey Perry",
          "   2. Teemu Selanne",
          "D  1. Lubomir Visnovsky",
          "   2. Toni Lydman",
          "G  1. Jonas Hiller",
          "   2. Curtis McElhinney",
          "ATL",
          "C 1. Nik Antropov",
          "  2. Bryan Little",
          "LW 1. Andrew Ladd",
          "   2. Evander Kane",
          "RW 1. Fredrik Modin",
          "   2. Niklas Bergfors",
          "D  1. Tobias Enstrom",
          "   2. Zack Bogosian",
          "G  1. Chris Mason",
          "   2. Ondrej Pavelec",
          "BOS",
          "C 1. David Krejci",
          "  2. Patrice Bergeron",
          "LW 1. Milan Lucic",
          "   2. Blake Wheeler",
          "RW 1. Nathan Horton",
          "   2. Mark Recchi",
          "D  1. Zdeno Chara",
          "   2. Dennis Seidenberg",
          "G  1. Tim Thomas",
          "   2. Tuukka Rask",
          endOfInputIndicator);
    assertEquals(Lists.newArrayList(
          new Player("Ryan", "Getzlaf", "ANA", "F"),
          new Player("Saku", "Koivu", "ANA", "F"),
          new Player("Bobby", "Ryan", "ANA", "F"),
          new Player("Jason", "Blake", "ANA", "F"),
          new Player("Corey", "Perry", "ANA", "F"),
          new Player("Teemu", "Selanne", "ANA", "F"),
          new Player("Lubomir", "Visnovsky", "ANA", "D"),
          new Player("Toni", "Lydman", "ANA", "D"),
          new Player("Jonas", "Hiller", "ANA", "G"),
          new Player("Curtis", "McElhinney", "ANA", "G"),
          new Player("Nik", "Antropov", "ATL", "F"),
          new Player("Bryan", "Little", "ATL", "F"),
          new Player("Andrew", "Ladd", "ATL", "F"),
          new Player("Evander", "Kane", "ATL", "F"),
          new Player("Fredrik", "Modin", "ATL", "F"),
          new Player("Niklas", "Bergfors", "ATL", "F"),
          new Player("Tobias", "Enstrom", "ATL", "D"),
          new Player("Zack", "Bogosian", "ATL", "D"),
          new Player("Chris", "Mason", "ATL", "G"),
          new Player("Ondrej", "Pavelec", "ATL", "G"),
          new Player("David", "Krejci", "BOS", "F"),
          new Player("Patrice", "Bergeron", "BOS", "F"),
          new Player("Milan", "Lucic", "BOS", "F"),
          new Player("Blake", "Wheeler", "BOS", "F"),
          new Player("Nathan", "Horton", "BOS", "F"),
          new Player("Mark", "Recchi", "BOS", "F"),
          new Player("Zdeno", "Chara", "BOS", "D"),
          new Player("Dennis", "Seidenberg", "BOS", "D"),
          new Player("Tim", "Thomas", "BOS", "G"),
          new Player("Tuukka", "Rask", "BOS", "G")),
          new PlayersSupplier(bufferedReader).get());
    verify(bufferedReader).close();
  }
}
