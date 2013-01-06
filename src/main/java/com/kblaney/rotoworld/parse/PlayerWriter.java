package com.kblaney.rotoworld.parse;

import java.io.IOException;

interface PlayerWriter
{
  void write(Player player) throws IOException;

  void close() throws IOException;
}
