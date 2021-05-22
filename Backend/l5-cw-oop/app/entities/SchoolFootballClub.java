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
 * The SchoolFootballClub class to store school football clubs.
 */
public class SchoolFootballClub extends FootballClub implements Serializable {
    private String schoolName;

    /**
     * Default Constructor to initialize SchoolFootballClub.
     */
    public SchoolFootballClub() {

    }

    /**
     * The method used for comparing two SchoolFootballClub objects.
     *
     * @param that SchoolFootballClub object to compare with.
     * @return Returns an Integer ('0' if equal in value, '-1' if lesser, '1' if greater).
     */
    public int compareTo(SchoolFootballClub that) {
        return this.schoolName.compareTo(that.schoolName);
    }

    /**
     * The Method used to check if two SchoolFootballClub objects are equal.
     *
     * @param o SchoolFootballClub object to check whether equal.
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
            SchoolFootballClub that = (SchoolFootballClub) o;
            return schoolName.equals(that.schoolName);
        }
    }

    /**
     * The hashCode method for SchoolFootballClub object.
     *
     * @return Returns an unique integer value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), schoolName);
    }

    /**
     * The Method to make a SchoolFootballClub object a String.
     *
     * @return Returns a String containing all the details of the SchoolFootballClub object.
     */
    public String toString() {
        return "SchoolFootballClub{" +
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
                ", schoolName='" + schoolName + '\'' +
                '}';
    }

    /**
     * Getter method for schoolName of the SchoolFootballClub object.
     *
     * @return Returns the schoolName of the SchoolFootballClub object.
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * Setter method for schoolName of the SchoolFootballClub object.
     *
     * @param schoolName The schoolName of the SchoolFootballClub object.
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
