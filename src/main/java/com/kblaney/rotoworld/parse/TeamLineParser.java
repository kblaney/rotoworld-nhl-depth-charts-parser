package com.kblaney.rotoworld.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class TeamLineParser
{
  private static final Pattern PATTERN = Pattern.compile("^([A-Z]{2,})$");
  private static final int GROUP_NUM = 1;

  public boolean doesLineHaveTeamShortform(final String line)
  {
    return PATTERN.matcher(line).matches();
  }

  public String getTeamShortform(final String line)
  {
    final Matcher m = PATTERN.matcher(line);
    if (m.matches())
    {
      return m.group(GROUP_NUM);
    }
    else
    {
      throw new IllegalArgumentException("Team shortform not found: " + line);
    }
  }
}
