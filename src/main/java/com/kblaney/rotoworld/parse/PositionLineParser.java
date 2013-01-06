package com.kblaney.rotoworld.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class PositionLineParser
{
  private static final Pattern PATTERN = Pattern.compile("^(C|LW|RW|D|G)");
  private static final int GROUP_NUM = 1;

  public boolean doesLineHavePosition(final String line)
  {
    return PATTERN.matcher(line).find();
  }

  public String getPositionShortform(final String line)
  {
    final Matcher m = PATTERN.matcher(line);
    if (m.find())
    {
      final String shortform = m.group(GROUP_NUM);
      if (shortform.equals("C") ||
            shortform.equals("RW") ||
            shortform.equals("LW"))
      {
        return "F";
      }
      else if (shortform.equals("D"))
      {
        return "D";
      }
      else if (shortform.equals("G"))
      {
        return "G";
      }
      else
      {
        throw new IllegalStateException("Illegal position: " + line);
      }
    }
    else
    {
      throw new IllegalArgumentException("Line pattern not found: " + line);
    }
  }
}
