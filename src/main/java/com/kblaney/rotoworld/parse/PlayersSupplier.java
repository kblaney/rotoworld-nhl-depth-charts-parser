package com.kblaney.rotoworld.parse;

import com.google.common.collect.Lists;
import com.kblaney.commons.lang.ArgAssert;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

final class PlayersSupplier
{
  private final BufferedReader bufferedReader;

  public PlayersSupplier(final Reader reader)
  {
    this(new BufferedReader(ArgAssert.notNull(reader, "reader")));
  }

  public PlayersSupplier(final BufferedReader bufferedReader)
  {
    this.bufferedReader = bufferedReader;
  }

  public List<Player> get() throws IOException
  {
    try
    {
      final List<Player> players = Lists.newArrayList();
      String teamShortform = null;
      String positionShortform = null;

      String line = bufferedReader.readLine();
      while (line != null)
      {
        if (doesLineHaveTeamShortform(line))
        {
          teamShortform = getTeamShortform(line);
        }
        else
        {
          if (doesLineHavePosition(line))
          {
            positionShortform = getPositionShortform(line);
          }
          final String firstName = getFirstName(line);
          final String lastName = getLastName(line);
          players.add(new Player(firstName, lastName, teamShortform, positionShortform));
        }

        line = bufferedReader.readLine();
      }
      return players;
    }
    finally
    {
      bufferedReader.close();
    }
  }

  private boolean doesLineHaveTeamShortform(final String line)
  {
    return new TeamLineParser().doesLineHaveTeamShortform(line);
  }

  private String getTeamShortform(final String line)
  {
    return new TeamLineParser().getTeamShortform(line);
  }

  private boolean doesLineHavePosition(final String line)
  {
    return new PositionLineParser().doesLineHavePosition(line);
  }

  private String getPositionShortform(final String line)
  {
    return new PositionLineParser().getPositionShortform(line);
  }

  private String getFirstName(final String line)
  {
    return new FirstNameLineParser().getFirstName(line);
  }

  private String getLastName(final String line)
  {
    return new LastNameLineParser().getLastName(line);
  }
}
