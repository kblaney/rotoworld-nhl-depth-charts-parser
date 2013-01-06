package com.kblaney.rotoworld.parse;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class EntryPoint
{
  public static void main(final String[] cmdLineArgs) throws IOException
  {
    final List<Player> players = readPlayersFrom("C:\\hockeypool\\rotoWorldDepthCharts.txt");
    writePlayers(players, "C:\\hockeypool\\regularSeasonPlayers.csv.txt");
  }

  private static List<Player> readPlayersFrom(final String fileSpec) throws IOException
  {
    return new PlayersSupplier(new FileReader(fileSpec)).get();
  }

  private static void writePlayers(final List<Player> players, final String fileSpec) throws IOException
  {
    new PlayersWriter(new FileWriter(fileSpec)).write(players);
  }
}
