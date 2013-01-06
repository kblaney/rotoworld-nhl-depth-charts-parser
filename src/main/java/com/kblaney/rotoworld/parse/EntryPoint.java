package com.kblaney.rotoworld.parse;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class EntryPoint
{
  public static void main(final String[] cmdLineArgs) throws IOException
  {
    final List<Player> players = new PlayersSupplier(new FileReader(
          "C:\\hockeypool\\rotoWorldDepthCharts.txt")).get();
    new PlayersWriter(new FileWriter(
          "C:\\hockeypool\\regularSeasonPlayers.csv.txt")).write(players);
  }
}
