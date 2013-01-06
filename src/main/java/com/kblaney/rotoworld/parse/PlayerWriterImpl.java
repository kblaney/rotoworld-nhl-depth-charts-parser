package com.kblaney.rotoworld.parse;

import com.kblaney.commons.lang.ArgAssert;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

final class PlayerWriterImpl implements PlayerWriter
{
  private final BufferedWriter bufferedWriter;

  public PlayerWriterImpl(final Writer writer)
  {
    bufferedWriter = new BufferedWriter(ArgAssert.notNull(writer, "writer"));
  }

  public void write(final Player player) throws IOException
  {
    bufferedWriter.write(player.getFirstName());
    bufferedWriter.write(",");
    bufferedWriter.write(player.getLastName());
    bufferedWriter.write(",");
    bufferedWriter.write(player.getTeamShortform());
    bufferedWriter.write(",");
    bufferedWriter.write(player.getPositionShortform());
    bufferedWriter.newLine();
  }

  public void close() throws IOException
  {
    bufferedWriter.close();
  }
}
