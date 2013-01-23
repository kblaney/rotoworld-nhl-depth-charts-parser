package com.kblaney.rotoworld.parse;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class EntryPoint
{
  public static void main(final String[] cmdLineArgs) throws IOException
  {
    final List<Player> players = readPlayersFrom("C:/data/hockeypool/rotoWorldDepthCharts.txt");
    writePlayers(players, "C:/data/hockeypool/regularSeasonPlayers.csv.txt");
    System.out.println("Done.");
  }

  private static List<Player> readPlayersFrom(final String fileSpec) throws IOException
  {
    System.out.println("Reading players from " + fileSpec + "...");
    return new PlayersSupplier(new FileReader(fileSpec)).get();
  }

  private static void writePlayers(final List<Player> players, final String fileSpec) throws IOException
  {
    System.out.println("Writing players to " + fileSpec + "...");
    new PlayersWriter(new FileWriter(fileSpec)).write(players);
  }
}
