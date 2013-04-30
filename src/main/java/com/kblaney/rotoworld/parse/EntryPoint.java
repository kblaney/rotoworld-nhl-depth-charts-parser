package com.kblaney.rotoworld.parse;

import com.google.common.base.Charsets;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;

public final class EntryPoint
{
  private static final Charset CHARSET = Charsets.UTF_8;
  private EntryPoint()
  {
  }

  public static void main(final String[] cmdLineArgs) throws IOException
  {
    final List<Player> players = readPlayersFrom("C:/data/hockeypool/rotoWorldDepthCharts-2013-Playoffs.txt");
    writePlayers(players, "C:/data/hockeypool/players-2013-Playoffs.csv.txt");
    System.out.println("Done.");
  }

  private static List<Player> readPlayersFrom(final String fileSpec) throws IOException
  {
    System.out.println("Reading players from " + fileSpec + "...");
    return new PlayersSupplier(new InputStreamReader(new FileInputStream(fileSpec), CHARSET)).get();
  }

  private static void writePlayers(final List<Player> players, final String fileSpec) throws IOException
  {
    System.out.println("Writing players to " + fileSpec + "...");
    new PlayersWriter(new OutputStreamWriter(new FileOutputStream(fileSpec), CHARSET)).write(players);
  }
}
