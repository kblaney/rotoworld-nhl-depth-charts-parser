package com.kblaney.rotoworld.parse;

import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public final class PlayerWriterImplTest
{
  @Test
  public void write_success() throws Exception
  {
    final Writer writer = new StringWriter();
    final Player player = new Player("Wayne", "Gretzky", "EDM", "F");
    final PlayerWriter playerWriter = new PlayerWriterImpl(writer);
    playerWriter.write(player);
    playerWriter.close();
    assertEquals("Wayne,Gretzky,EDM,F" + System.getProperty("line.separator"), writer.toString());
  }

  @Test
  public void close_success() throws Exception
  {
    final Writer writer = mock(Writer.class);
    new PlayerWriterImpl(writer).close();
    verify(writer).close();
  }
}
