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
import java.util.Random;

/**
 * The Match class to store matches.
 *
 * @param <T> SportClub type.
 */
public class Match<T extends SportsClub> implements Serializable {
    private int scoreHome;
    private int scoreAway;
    private boolean isDraw;
    private Date date;
    private T clubHome;
    private T clubAway;

    /**
     * Default Constructor for Match.
     */
    public Match() {

    }

    /**
     * An argument constructor for Match (Auto Generate scores).
     *
     * @param date     The date of the Match.
     * @param clubHome The home club of the Match.
     * @param clubAway The away club of the Match.
     */
    public Match(Date date, T clubHome, T clubAway) {
        Random rand = new Random();

        this.date = date;
        this.clubHome = clubHome;
        this.clubAway = clubAway;

        // Creating score for home team.
        this.scoreHome = rand.nextInt(15);
        // Creating score for away team.
        this.scoreAway = rand.nextInt(15);

        this.isDraw = (this.scoreHome == this.scoreAway);
    }

    /**
     * An argument constructor for Match.
     *
     * @param scoreHome The score of the home club in the Match.
     * @param scoreAway The score of the away club in the Match.
     * @param date      The date of the Match.
     * @param clubHome  The home club of the Match.
     * @param clubAway  The away club of the Match.
     */
    public Match(Date date, T clubHome, T clubAway, int scoreHome, int scoreAway) {
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
        this.date = date;
        this.clubHome = clubHome;
        this.clubAway = clubAway;
        // Checking if the match is draw;
        this.isDraw = (scoreHome == scoreAway);
    }

    /**
     * The method used for comparing two Match objects.
     *
     * @param match Match object to compare with.
     * @return Returns an Integer ('0' if equal in value, '-1' if lesser, '1' if greater).
     */
    public int compareTo(Match<T> match) {
        // Comparing date of the Match.
        return match.date.compareTo(match.getDate());
    }

    /**
     * The Method used to check if two Match objects are equal.
     *
     * @param o Match object to check whether equal.
     * @return Returns a boolean value ('true' if equal, 'false' if unequal).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match<?> match = (Match<?>) o;
        return date.equals(match.date) &&
                clubHome.equals(match.clubHome) &&
                clubAway.equals(match.clubAway);
    }

    /**
     * The hashCode method for Match object.
     *
     * @return Returns an unique integer value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(date, clubHome, clubAway);
    }

    /**
     * The Method to make a Match object a String.
     *
     * @return Returns a String containing all the details of the Match object.
     */
    @Override
    public String toString() {
        return "Match{" +
                "scoreHome=" + scoreHome +
                ", scoreAway=" + scoreAway +
                ", isDraw=" + isDraw +
                ", date=" + date +
                ", clubHome=" + clubHome +
                ", clubAway=" + clubAway +
                '}';
    }

    /**
     * Getter method for the scoreHome of the Match object.
     *
     * @return Returns the scoreHome of the Match object.
     */
    public int getScoreHome() {
        return scoreHome;
    }

    /**
     * Setter method for the scoreHome of the Match object.
     *
     * @param scoreHome The scoreHome of the Match object.
     */
    public void setScoreHome(int scoreHome) {
        this.scoreHome = scoreHome;
    }

    /**
     * Getter method for the scoreAway of the Match object.
     *
     * @return Returns the scoreAway of the Match object.
     */
    public int getScoreAway() {
        return scoreAway;
    }

    /**
     * Setter method for the scoreAway of the Match object.
     *
     * @param scoreAway The scoreAway of the Match object.
     */
    public void setScoreAway(int scoreAway) {
        this.scoreAway = scoreAway;
    }

    /**
     * Getter method for the isDraw of the Match object.
     *
     * @return Returns the isDraw of the Match object.
     */
    public boolean isDraw() {
        return isDraw;
    }

    /**
     * Setter method for the isDraw of the Match object.
     *
     * @param draw The isDraw of the Match object.
     */
    public void setDraw(boolean draw) {
        isDraw = draw;
    }

    /**
     * Getter method for the date of the Match object.
     *
     * @return Returns the date of the Match object.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter method for the date of the Match object.
     *
     * @param date The date of the Match object.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter method for the clubHome of the Match object.
     *
     * @return Returns the clubHome of the Match object.
     */
    public T getClubHome() {
        return clubHome;
    }

    /**
     * Setter method for the clubHome of the Match object.
     *
     * @param clubHome The clubHome of the Match object.
     */
    public void setClubHome(T clubHome) {
        this.clubHome = clubHome;
    }

    /**
     * Getter method for the clubAway of the Match object.
     *
     * @return Returns the clubAway of the Match object.
     */
    public T getClubAway() {
        return clubAway;
    }

    /**
     * Setter method for the clubAway of the Match object.
     *
     * @param clubAway The clubAway of the Match object.
     */
    public void setClubAway(T clubAway) {
        this.clubAway = clubAway;
    }
}
