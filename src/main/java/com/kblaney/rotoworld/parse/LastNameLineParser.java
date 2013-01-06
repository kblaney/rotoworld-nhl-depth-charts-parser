package com.kblaney.rotoworld.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class LastNameLineParser
{
  // Lines are as follows:
  //  "RW   1.  Chris Stewart"
  //  "     5.  David Van der Gulik"
  //
  private static final Pattern PATTERN = Pattern.compile(
        "\\d+\\.\\s+\\S+\\s+(.+)$");
  private static final int GROUP_NUM = 1;

  public String getLastName(final String line)
  {
    final Matcher m = PATTERN.matcher(line);
    if (m.find())
    {
      return m.group(GROUP_NUM);
    }
    else
    {
      throw new IllegalArgumentException("Last name not found in: " + line);
    }
  }
}
