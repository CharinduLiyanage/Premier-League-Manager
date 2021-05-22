/*
 * Full name    : Charindu Supun Nauththuduwa Lianage
 * IIT number   : 2018411
 * UOW number   : w1761962
 * <p>
 * I confirm that I understand what plagiarism /
 * collusion / contract cheating is and have read and
 * understood the section on Assessment Offences in the
 * Essential Information for Students. The work that I
 * have submitted is entirely my own. Any work from
 * other authors is duly referenced and acknowledged.
 */

package entities;

// Imports.

import java.io.Serializable;
import java.util.Objects;

/**
 * The FootballClub class to store university football clubs.
 */
public class UniversityFootballClub extends FootballClub implements Serializable {
    private String universityName;

    /**
     * Default Constructor for UniversityFootballClub.
     */
    public UniversityFootballClub() {
    }

    /**
     * The method used for comparing two UniversityFootballClub objects.
     *
     * @param that UniversityFootballClub object to compare with.
     * @return Returns an Integer ('0' if equal in value, '-1' if lesser, '1' if greater).
     */
    public int compareTo(UniversityFootballClub that) {
        // Comparing university names.
        return this.universityName.compareTo(that.universityName);
    }

    /**
     * The Method used to check if two UniversityFootballClub objects are equal.
     *
     * @param o UniversityFootballClub object to check whether equal.
     * @return Returns a boolean value ('true' if equal, 'false' if unequal).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else if (!super.equals(o)) {
            return false;
        } else {
            UniversityFootballClub that = (UniversityFootballClub) o;
            return universityName.equals(that.universityName);
        }
    }

    /**
     * The hashCode method for UniversityFootballClub object.
     *
     * @return Returns an unique integer value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), universityName);
    }

    /**
     * The Method to make a UniversityFootballClub object a String.
     *
     * @return Returns a String containing all the details of the UniversityFootballClub object.
     */
    @Override
    public String toString() {
        return "UniversityFootballClub{" +
                "name='" + this.getName() + '\'' +
                ", location='" + this.getLocation() + '\'' +
                ", matchesPlayed=" + this.getMatchesPlayed() +
                ", wins=" + this.getWins() +
                ", draws=" + this.getDraws() +
                ", losses=" + this.getLosses() +
                ", goalsFor=" + this.getGoalsFor() +
                ", goalsAgainst=" + this.getGoalsAgainst() +
                ", goalDifference=" + this.getGoalDifference() +
                ", points=" + this.getPoints() +
                ", universityName='" + universityName + '\'' +
                '}';
    }

    /**
     * Getter method for universityName of the UniversityFootballClub object.
     *
     * @return Returns the universityName of the UniversityFootballClub object.
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * Setter method for universityName of the UniversityFootballClub object.
     *
     * @param universityName The universityName of the UniversityFootballClub object.
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
}
