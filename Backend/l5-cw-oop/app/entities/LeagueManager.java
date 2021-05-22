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

/**
 * Interface for the sports leagues.
 *
 * @param <T> Type of the Sport.
 */
public interface LeagueManager<T extends SportsClub> {

    /**
     * Method to add club to the CLUBS arraylist.
     *
     * @param sportsClub SportClub to be added.
     */
    void addToClubList(T sportsClub);

    /**
     * Method to remove club from the CLUBS arraylist.
     *
     * @param clubName Name of the club to be removed.
     * @param location Location of the Club to be removed.
     * @return Returns a boolean, status of removal ('true' if removed, 'false' if not removed).
     */
    boolean deleteFromClubList(String clubName, String location);

    /**
     * Method to add a match to the MATCHES arraylist.
     *
     * @param match Match to be added.
     */
    boolean addMatch(Match<T> match);

    /**
     * Method to display details of a selected club.
     *
     * @param club sportsClub to be displayed.
     * @return Returns a String containing the details.
     */
    String displayClub(T club);

    /**
     * Method to display the league table.
     *
     * @return Returns the String table containing the details of the league.
     */
    String displayLeagueTable();
}
