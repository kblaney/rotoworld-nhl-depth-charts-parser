package com.kblaney.rotoworld.parse;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public final class PlayersWriterTest
{
  private Player player;

  @Before
  public void setUp()
  {
    player = new Player("Wayne", "Gretzky", "EDM", "F");
  }

  @Test(expected = IOException.class)
  public void write_failure() throws Exception
  {
    final PlayerWriter playerWriter = mock(PlayerWriter.class);
    doThrow(new IOException()).when(playerWriter).write(any(Player.class));
    new PlayersWriter(playerWriter).write(Lists.newArrayList(player));
    verify(playerWriter).close();
  }

  @Test
  public void write_emptyStringWriter() throws Exception
  {
    final StringWriter stringWriter = new StringWriter();
    new PlayersWriter(stringWriter).write(Lists.<Player>newArrayList());
    assertEquals("", stringWriter.toString());
  }

  @Test
  public void write_zeroPlayers() throws Exception
  {
    final PlayerWriter playerWriter = mock(PlayerWriter.class);
    new PlayersWriter(playerWriter).write(Lists.<Player>newArrayList());
    verify(playerWriter).close();
    verifyNoMoreInteractions(playerWriter);
  }

  @Test
  public void write_onePlayer() throws Exception
  {
    final PlayerWriter playerWriter = mock(PlayerWriter.class);
    new PlayersWriter(playerWriter).write(Lists.newArrayList(player));
    verify(playerWriter).write(player);
    verify(playerWriter).close();
  }

  @Test
  public void write_threePlayers() throws Exception
  {
    final Player secondPlayer = new Player("Jari", "Kurri", "EDM", "F");
    final Player thirdPlayer = new Player("Billy", "Smith", "NYI", "G");
    final PlayerWriter playerWriter = mock(PlayerWriter.class);
    new PlayersWriter(playerWriter).write(Lists.newArrayList(player,
          secondPlayer, thirdPlayer));
    verify(playerWriter).write(player);
    verify(playerWriter).write(secondPlayer);
    verify(playerWriter).write(thirdPlayer);
    verify(playerWriter).close();
  }
}
