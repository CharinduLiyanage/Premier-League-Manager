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
 * The FootballClub class to store football clubs.
 */
public class FootballClub extends SportsClub implements Serializable {
    private int matchesPlayed;
    private int wins;
    private int draws;
    private int losses;
    private int goalsFor;
    private int goalsAgainst;
    private int goalDifference;
    private int points;

    /**
     * Default Constructor for FootballClub.
     */
    public FootballClub() {

    }

    /**
     * An argument constructor for FootballClub.
     *
     * @param name     The name of the FootballClub.
     * @param location The location of the FootballClub.
     */
    public FootballClub(String name, String location) {
        super(name, location);
    }

    /**
     * An argument constructor for FootballClub.
     *
     * @param name         The name of the FootballClub.
     * @param location     The location of the FootballClub.
     * @param wins         The number of wins of the FootballClub.
     * @param draws        The number of draws of the FootballClub.
     * @param losses       The number of losses of the FootballClub.
     * @param goalsFor     The number of goals the FootballClub has played against other teams.
     * @param goalsAgainst The number of goals other teams have played against the FootballClub.
     */
    public FootballClub(String name, String location, int wins, int draws, int losses, int goalsFor, int goalsAgainst) {
        super(name, location);
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.goalsAgainst = goalsAgainst;
        this.goalsFor = goalsFor;
        // Generating goal difference.
        this.goalDifference = goalsFor - goalsAgainst;
        // Generating the number of played matches.
        this.matchesPlayed = wins + losses + draws;
        // Generating the number of point.
        this.points = (wins * 3) + draws;
    }

    /**
     * The method used for comparing two FootballClub objects.
     *
     * @param that FootballClub object to compare with.
     * @return Returns an Integer ('0' if equal in value, '-1' if lesser, '1' if greater).
     */
    public int compareTo(FootballClub that) {
        // Comparing points.
        int compare = Integer.compare(that.points, this.points);
        // Comparing goal difference if points is equal.
        if (compare == 0) {
            compare = Integer.compare(that.goalDifference, this.goalDifference);
        }
        return compare;
    }

    /**
     * The Method used to check if two FootballClub objects are equal.
     *
     * @param o FootballClub object to check whether equal.
     * @return Returns a boolean value ('true' if equal, 'false' if unequal).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FootballClub that = (FootballClub) o;
        return wins == that.wins &&
                draws == that.draws &&
                losses == that.losses &&
                goalsAgainst == that.goalsAgainst &&
                goalsFor == that.goalsFor &&
                goalDifference == that.goalDifference &&
                matchesPlayed == that.matchesPlayed &&
                points == that.points;
    }

    /**
     * The hashCode method for FootballClub object.
     *
     * @return Returns an unique integer value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wins, draws, losses, goalsAgainst, goalsFor, goalDifference, matchesPlayed, points);
    }

    /**
     * The Method to make a FoodBallClub object a String.
     *
     * @return Returns a String containing all the details of the FootballClub object.
     */
    @Override
    public String toString() {
        return "FootballClub{" +
                "name='" + this.getName() + '\'' +
                ", location='" + this.getLocation() + '\'' +
                ", matchesPlayed=" + matchesPlayed +
                ", wins=" + wins +
                ", draws=" + draws +
                ", losses=" + losses +
                ", goalsFor=" + goalsFor +
                ", goalsAgainst=" + goalsAgainst +
                ", goalDifference=" + goalDifference +
                ", points=" + points +
                '}';
    }

    /**
     * Getter method for the wins of the FootballClub object.
     *
     * @return Returns the wins of the FootballClub object.
     */
    public int getWins() {
        return wins;
    }

    /**
     * Setter method for the wins of the FootballClub object.
     *
     * @param wins The wins of the FootballClub object.
     */
    public void setWins(int wins) {
        // Updating matches played according to wins.
        int winDifference = wins - this.wins;
        this.matchesPlayed += winDifference;
        this.points += (winDifference * 3);
        this.wins = wins;
    }

    /**
     * Getter method for the draws of the FootballClub object.
     *
     * @return Returns the draws of the FootballClub object.
     */
    public int getDraws() {
        return draws;
    }

    /**
     * Setter method for the draws of the FootballClub object.
     *
     * @param draws The draws of the FootballClub object.
     */
    public void setDraws(int draws) {
        // Updating matches played according to draws.
        int drawDifference = draws - this.draws;
        this.matchesPlayed += drawDifference;
        this.points += drawDifference;
        this.draws = draws;
    }

    /**
     * Getter method for the losses of the FootballClub object.
     *
     * @return Returns the losses of the FootballClub object.
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Setter method for the losses of the FootballClub object.
     *
     * @param losses The losses of the FootballClub object.
     */
    public void setLosses(int losses) {
        // Updating matches played according to losses.
        int lossDifference = losses - this.losses;
        this.matchesPlayed += lossDifference;
        this.losses = losses;
    }

    /**
     * Getter method for the goalsAgainst of the FootballClub object.
     *
     * @return Returns the goalsAgainst of the FootballClub object.
     */
    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    /**
     * Setter method for the goalsAgainst of the FootballClub object.
     *
     * @param goalsAgainst The goalsAgainst of the FootballClub object.
     */
    public void setGoalsAgainst(int goalsAgainst) {
        // Updating goal difference according to goals Against.
        int goalDifference = goalsAgainst - this.goalsAgainst;
        this.goalDifference -= goalDifference;
        this.goalsAgainst = goalsAgainst;
    }

    /**
     * Getter method for the goalsFor of the FootballClub object.
     *
     * @return Returns the goalsFor of the FootballClub object.
     */
    public int getGoalsFor() {
        return goalsFor;
    }

    /**
     * Setter method for the goalsFor of the FootballClub object.
     *
     * @param goalsFor The goalsFor of the FootballClub object.
     */
    public void setGoalsFor(int goalsFor) {
        // Updating goal difference according to goals for.
        int goalDifference = goalsFor - this.goalsFor;
        this.goalDifference += goalDifference;
        this.goalsFor = goalsFor;
    }

    /**
     * Getter method for the goalDifference of the FootballClub object.
     *
     * @return Returns the goalDifference of the FootballClub object.
     */
    public int getGoalDifference() {
        return goalDifference;
    }

    /**
     * Setter method for the goalDifference of the FootballClub object.
     *
     * @param goalDifference The goalDifference of the FootballClub object.
     */
    public void setGoalDifference(int goalDifference) {
        // Checking if the setting goalDifference is correct according to wins and draws.
        if (goalDifference != (this.goalsFor - this.goalsAgainst)) {
            System.out.println("WARNING - Goal Difference unequal to the played matches.");
        }
        this.goalDifference = goalDifference;
    }

    /**
     * Getter method for the matchesPlayed of the FootballClub object.
     *
     * @return Returns the matchesPlayed of the FootballClub object.
     */
    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    /**
     * Setter method for the matchesPlayed of the FootballClub object.
     *
     * @param matchesPlayed The matchesPlayed of the FootballClub object.
     */
    public void setMatchesPlayed(int matchesPlayed) {
        if (matchesPlayed == wins + draws + losses) {
            this.matchesPlayed = matchesPlayed;
        } else {
            System.out.println("[WARNING] FootballClub - Did not set matchesPlayed,The Number of points unequal to the sum of wins, draws and losses.");
        }
    }

    /**
     * Getter method for the points of the FootballClub object.
     *
     * @return Returns the points of the FootballClub object.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Setter method for the points of the FootballClub object.
     *
     * @param points The points of the FootballClub object.
     */
    public void setPoints(int points) {
        // Checking if the setting points is correct according to wins and draws.
        if (points != ((wins * 3) + draws)) {
            System.out.println("[WARNING] FootballClub - Did not set points, The Number of points unequal to the played matches.");
        } else {
            this.points = points;
        }
    }
}
