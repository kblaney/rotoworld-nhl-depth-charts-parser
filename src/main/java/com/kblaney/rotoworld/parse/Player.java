package com.kblaney.rotoworld.parse;

import com.kblaney.assertions.ArgAssert;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

final class Player
{
  private final String firstName;
  private final String lastName;
  private final String teamShortform;
  private final String positionShortform;

  public Player(final String firstName, final String lastName, final String teamShortform,
        final String positionShortform)
  {
    this.firstName = ArgAssert.assertNotNull(firstName, "firstName");
    this.lastName = ArgAssert.assertNotNull(lastName, "lastName");
    this.teamShortform = ArgAssert.assertNotNull(teamShortform, "teamShortform");
    this.positionShortform = ArgAssert.assertNotNull(positionShortform, "positionShortform");
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getTeamShortform()
  {
    return teamShortform;
  }

  public String getPositionShortform()
  {
    return positionShortform;
  }

  @Override
  public boolean equals(final Object thatObject)
  {
    if (thatObject == null)
    {
      return false;
    }
    else
    {
      if (thatObject.getClass() == getClass())
      {
        final Player that = (Player) thatObject;
        return new EqualsBuilder().append(firstName, that.firstName).append(lastName, that.lastName)
              .append(teamShortform, that.teamShortform).append(positionShortform, that.positionShortform).isEquals();
      }
      else
      {
        return false;
      }
    }
  }

  @Override
  public int hashCode()
  {
    return new HashCodeBuilder().append(firstName).append(lastName).append(teamShortform).append(positionShortform)
          .toHashCode();
  }

  @Override
  public String toString()
  {
    return firstName + " " + lastName + ":" + positionShortform + ":" + teamShortform;
  }
}
