package com.kblaney.rotoworld.parse;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

final class PlayersWriter
{
  private final PlayerWriter playerWriter;

  public PlayersWriter(final Writer writer)
  {
    this(new PlayerWriterImpl(writer));
  }

  PlayersWriter(final PlayerWriter playerWriter)
  {
    this.playerWriter = playerWriter;
  }

  public void write(final List<Player> players) throws IOException
  {
    try
    {
      for (final Player player : players)
      {
        playerWriter.write(player);
      }
    }
    finally
    {
      playerWriter.close();
    }
  }
}
