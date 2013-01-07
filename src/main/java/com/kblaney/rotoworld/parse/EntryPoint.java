package com.kblaney.rotoworld.parse;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class EntryPoint
{
  public static void main(final String[] cmdLineArgs) throws IOException
  {
    final String inputFileSpec = "C:/data/hockeypool/rotoWorldDepthCharts.txt";
    System.out.println("Reading players from " + inputFileSpec + "...");
    final List<Player> players = readPlayersFrom(inputFileSpec);

    final String outputFileSpec = "C:/data/hockeypool/regularSeasonPlayers.csv.txt";
    System.out.println("Writing players to " + outputFileSpec + "...");
    writePlayers(players, outputFileSpec);
    System.out.println("Done.");
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
