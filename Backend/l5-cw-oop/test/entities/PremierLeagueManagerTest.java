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

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class to test PremierLeagueManager class.

 */
class PremierLeagueManagerTest {

    /**
     * Method to test PremierLeagueManager.addToClubList() method.
     */
    @Test
    void addToClubList() {
        // Initialising testing PremierLeagueManager.
        PremierLeagueManager plm = new PremierLeagueManager(2020);

        // Invalid FootballClub, null FootballClub.
        FootballClub nullFootballClub = new FootballClub();
        plm.addToClubList(nullFootballClub);
        assertEquals(0, plm.getCLUBS().size());

        // Valid FootballClub, with name and location.
        FootballClub validFootballClub1 = new FootballClub("club name a", "club location a");
        plm.addToClubList(validFootballClub1);
        assertEquals(1, plm.getCLUBS().size());

        // Valid FootballClub with name, location, wins, draws, losses, goalsFor, and goalsAgainst.
        FootballClub validFootballClub2 = new FootballClub("club name b", "club location b", 1, 2, 3, 4, 5);
        plm.addToClubList(validFootballClub2);
        assertEquals(2, plm.getCLUBS().size());
    }

    /**
     * Method to test PremierLeagueManager.deleteFromClubList() method.
     */
    @Test
    void deleteFromClubList() {
        // Initialising testing PremierLeagueManager.
        PremierLeagueManager plm = new PremierLeagueManager(2020);

        // Adding a club to PremierLeagueManager to be deleted.
        FootballClub validFootballClub1 = new FootballClub("club name a", "club location a");
        plm.addToClubList(validFootballClub1);

        // Deleting the added FootballClub.
        plm.deleteFromClubList("club name a", "club location a");

        // Testing if all the clubs has being deleted.
        assertEquals(0, plm.getCLUBS().size());
    }

    /**
     * Method to test PremierLeagueManager.addMatch() method.
     */
    @Test
    void addMatch() {
        // Initialising testing PremierLeagueManager.
        PremierLeagueManager plm = new PremierLeagueManager(2020);

        // Adding FootballClubs to PremierLeagueManager.
        FootballClub footballClubA = new FootballClub("club  a", "location a");
        plm.addToClubList(footballClubA);
        FootballClub footballClubB = new FootballClub("club  b", "location b");
        plm.addToClubList(footballClubB);

        // Adding a new Match to PremierLeagueManager, away club wining situation.
        Match<FootballClub> match1 = new Match<>(new Date(2020, 2, 2), footballClubA, footballClubB, 6, 7);
        plm.addMatch(match1);

        // Testing if the match is added.
        assertEquals(1, plm.getMATCHES().size());
        // Testing whether the added match is the given match.
        assertEquals(match1, plm.getMATCHES().get(0));


        // Testing whether the data updates of the home club done correctly.
        assertEquals(1, footballClubA.getMatchesPlayed());
        assertEquals(1, footballClubA.getLosses());
        assertEquals(0, footballClubA.getWins());
        assertEquals(0, footballClubA.getDraws());
        assertEquals(6, footballClubA.getGoalsFor());
        assertEquals(7, footballClubA.getGoalsAgainst());
        assertEquals(-1, footballClubA.getGoalDifference());
        assertEquals(0, footballClubA.getPoints());

        // Testing whether the data updates of the away club done correctly.
        assertEquals(1, footballClubB.getMatchesPlayed());
        assertEquals(1, footballClubB.getWins());
        assertEquals(0, footballClubB.getLosses());
        assertEquals(0, footballClubB.getDraws());
        assertEquals(7, footballClubB.getGoalsFor());
        assertEquals(6, footballClubB.getGoalsAgainst());
        assertEquals(1, footballClubB.getGoalDifference());
        assertEquals(3, footballClubB.getPoints());

        // Replacing the previously added match to the PremierLeagueManager, home club winning situation.
        Match<FootballClub> match2 = new Match<>(new Date(2020, 2, 2), footballClubA, footballClubB, 11, 8);
        plm.addMatch(match2);

        // Testing whether there is only one match.
        assertEquals(1, plm.getMATCHES().size());
        // Testing whether the match has been updated.
        assertEquals(match2, plm.getMATCHES().get(0));

        // Testing whether the data updates of the home club done correctly.
        assertEquals(1, footballClubA.getMatchesPlayed());
        assertEquals(1, footballClubA.getWins());
        assertEquals(0, footballClubA.getLosses());
        assertEquals(0, footballClubA.getDraws());
        assertEquals(11, footballClubA.getGoalsFor());
        assertEquals(8, footballClubA.getGoalsAgainst());
        assertEquals(3, footballClubA.getGoalDifference());
        assertEquals(3, footballClubA.getPoints());

        // Testing whether the data updates of the away club done correctly.
        assertEquals(1, footballClubB.getMatchesPlayed());
        assertEquals(0, footballClubB.getWins());
        assertEquals(1, footballClubB.getLosses());
        assertEquals(0, footballClubB.getDraws());
        assertEquals(8, footballClubB.getGoalsFor());
        assertEquals(11, footballClubB.getGoalsAgainst());
        assertEquals(-3, footballClubB.getGoalDifference());
        assertEquals(0, footballClubB.getPoints());


        // Adding two more clubs to the PremierLeagueManger.
        FootballClub footballClubC = new FootballClub("club  c", "location c");
        plm.addToClubList(footballClubC);
        FootballClub footballClubD = new FootballClub("club  d", "location d");
        plm.addToClubList(footballClubD);

        // Adding a match to PremierLeagueManger played by the two clubs added above, draw situation.
        Match<FootballClub> match3 = new Match<>(new Date(2020, 2, 2), footballClubC, footballClubD, 10, 10);
        plm.addMatch(match3);

        // Testing Whether the match has been added.
        assertEquals(2, plm.getMATCHES().size());

        // Testing whether the data updates of the home club done correctly.
        assertEquals(1, footballClubC.getMatchesPlayed());
        assertEquals(0, footballClubC.getWins());
        assertEquals(0, footballClubC.getLosses());
        assertEquals(1, footballClubC.getDraws());
        assertEquals(10, footballClubC.getGoalsFor());
        assertEquals(10, footballClubC.getGoalsAgainst());
        assertEquals(0, footballClubC.getGoalDifference());
        assertEquals(1, footballClubC.getPoints());

        // Testing whether the data updates of the away club done correctly.
        assertEquals(1, footballClubD.getMatchesPlayed());
        assertEquals(0, footballClubD.getWins());
        assertEquals(0, footballClubD.getLosses());
        assertEquals(1, footballClubD.getDraws());
        assertEquals(10, footballClubD.getGoalsFor());
        assertEquals(10, footballClubD.getGoalsAgainst());
        assertEquals(0, footballClubD.getGoalDifference());
        assertEquals(1, footballClubD.getPoints());
    }

    /**
     * Method to test PremierLeagueManager.displayClub() method.
     */
    @Test
    void displayClub() {
        // Initialising testing PremierLeagueManager.
        PremierLeagueManager plm = new PremierLeagueManager();

        // Adding a club to PremierLeagueManager.
        FootballClub testFootballClub = new FootballClub("Test Name", "Test Location");
        plm.addToClubList(testFootballClub);

        // Expected output.
        String str = "\t|-------------------------------------|\n" +
                "\t| # Club            :  Test Name      |\n" +
                "\t| # location        :  Test Location  |\n" +
                "\t| # Matches Played  :  0              |\n" +
                "\t| # Wins            :  0              |\n" +
                "\t| # Draws           :  0              |\n" +
                "\t| # Losses          :  0              |\n" +
                "\t| # Goals For       :  0              |\n" +
                "\t| # Goals Against   :  0              |\n" +
                "\t| # Points          :  0              |\n" +
                "\t|-------------------------------------|";

        // Testing whether the expected output comes.
        assertEquals(str, plm.displayClub(testFootballClub));
    }

    /**
     * Method to test PremierLeagueManager.displayLeagueTable() method.
     */
    @Test
    void displayLeagueTable() {
        // Initialising testing PremierLeagueManager.
        PremierLeagueManager plm = new PremierLeagueManager();

        // Adding a club to PremierLeagueManager.
        FootballClub footballClub = new FootballClub("Test Name", "Test Location");
        plm.addToClubList(footballClub);

        // Expected output.
        String str = "|-----------|----------------|------|-------|--------|-----------|---------------" +
                "|-----------------|--------|---------------------|\n" +
                "| Club      | Matches Played | Wins | Draws | Losses | Goals For | Goals Against " +
                "| Goal Difference | Points | Last 5 Matches      |\n" +
                "|-----------|----------------|------|-------|--------|-----------|---------------" +
                "|-----------------|--------|+---+---+---+---+---+|\n" +
                "| Test Name |              0 |    0 |     0 |      0 |         0 |             0 " +
                "|               0 |      0 || - | - | - | - | - || \n" +
                "|-----------|----------------|------|-------|--------|-----------|---------------" +
                "|-----------------|--------|+---+---+---+---+---+|";

        // Testing whether the expected output comes.
        assertEquals(str, plm.displayLeagueTable());
    }

    /**
     * Method to test PremierLeagueManager.toString() method.
     */
    @Test
    void testToString() {
        // Declaring variable for the testing PremierLeagueManager.
        PremierLeagueManager plm;
        // Declaring the variable for the expected output.
        String output;

        // Initialising a PremierLeagueManager using the default constructor.
        plm = new PremierLeagueManager();
        // Expected out put for the above PremierLeagueManager.
        output = "PremierLeagueManager{CLUBS=[], MATCHES=[], year=0}";

        // Testing if the Expected outcome is coming.
        assertEquals(output, plm.toString());

        // Reassigning a PremierLeagueManager using the only constructor with year.
        plm = new PremierLeagueManager(2020);
        // Expected out put for the above PremierLeagueManager.
        output = "PremierLeagueManager{CLUBS=[], MATCHES=[], year=2020}";

        // Testing if the Expected outcome is coming.
        assertEquals(output, plm.toString());

        // Adding FootballClubs to the PremierLeagueManager.
        FootballClub footballClubA = new FootballClub("club  a", "location a");
        plm.addToClubList(footballClubA);
        FootballClub footballClubB = new FootballClub("club  b", "location b");
        plm.addToClubList(footballClubB);

        // Expected out put for the above PremierLeagueManager.
        output = "PremierLeagueManager{CLUBS=[FootballClub{name='club  a', location='location a', matchesPlayed=0, " +
                "wins=0, draws=0, losses=0, goalsFor=0, goalsAgainst=0, goalDifference=0, points=0}, " +
                "FootballClub{name='club  b', location='location b', matchesPlayed=0, wins=0, draws=0, losses=0, " +
                "goalsFor=0, goalsAgainst=0, goalDifference=0, points=0}], MATCHES=[], year=2020}";

        // Testing if the Expected outcome is coming.
        assertEquals(output, plm.toString());

        // Adding a match to the PremierLeagueManager.
        Match<FootballClub> match = new Match<>(new Date(2020, 2, 2), footballClubA, footballClubB, 1, 2);
        plm.addMatch(match);

        // Expected out put for the above PremierLeagueManager.
        output = "PremierLeagueManager{CLUBS=[FootballClub{name='club  a', location='location a', matchesPlayed=1, " +
                "wins=0, draws=0, losses=1, goalsFor=1, goalsAgainst=2, goalDifference=-1, points=0}, " +
                "FootballClub{name='club  b', location='location b', matchesPlayed=1, wins=1, draws=0, losses=0, " +
                "goalsFor=2, goalsAgainst=1, goalDifference=1, points=3}], MATCHES=[Match{scoreHome=1, scoreAway=2, " +
                "isDraw=false, date=Date{year=2020, month=2, day=2}, clubHome=FootballClub{name='club  a', " +
                "location='location a', matchesPlayed=1, wins=0, draws=0, losses=1, goalsFor=1, goalsAgainst=2, " +
                "goalDifference=-1, points=0}, clubAway=FootballClub{name='club  b', location='location b', " +
                "matchesPlayed=1, wins=1, draws=0, losses=0, goalsFor=2, goalsAgainst=1, goalDifference=1, " +
                "points=3}}], year=2020}";

        // Testing if the Expected outcome is coming.
        assertEquals(output, plm.toString());
    }

    /**
     * Method to test PremierLeagueManager.compareTo() method.
     */
    @Test
    void compareTo() {
        // Initialising main testing PremierLeagueManager.
        PremierLeagueManager plmMain = new PremierLeagueManager(2021);

        // Initialising testing PremierLeagueManager one year less than main testing PremierLeagueManager.
        PremierLeagueManager plm1 = new PremierLeagueManager(2020);
        // Initialising testing PremierLeagueManager one year more than main testing PremierLeagueManager.
        PremierLeagueManager plm2 = new PremierLeagueManager(2022);
        // Initialising testing PremierLeagueManager same year as main testing PremierLeagueManager.
        PremierLeagueManager plm3 = new PremierLeagueManager(2021);

        // Testing compareTo with one year less PremierLeagueManager.
        assertEquals(1, plmMain.compareTo(plm1));

        // Testing compareTo with one year more PremierLeagueManager.
        assertEquals(-1, plmMain.compareTo(plm2));

        // Testing compareTo with same year PremierLeagueManager.
        assertEquals(0, plmMain.compareTo(plm3));
    }

    /**
     * Method to test PremierLeagueManager.equals() method.
     */
    @Test
    void testEquals() {
        // Initialising main testing PremierLeagueManager.
        PremierLeagueManager plmMain = new PremierLeagueManager(2020);
        // Initialising testing PremierLeagueManager different year to main testing PremierLeagueManager.
        PremierLeagueManager plm2 = new PremierLeagueManager(2021);
        // Initialising testing PremierLeagueManager same year as main testing PremierLeagueManager.
        PremierLeagueManager plm3 = new PremierLeagueManager(2020);

        // Testing equals method of PremierLeagueManager, using not equal PremierLeagueManagers.
        assertFalse(plmMain.equals(plm2));
        // Testing equals method of PremierLeagueManager, using equal PremierLeagueManagers.
        assertTrue(plmMain.equals(plm3));
    }

    /**
     * Method to test PremierLeagueManager.hashCode() method.
     */
    @Test
    void testHashCode() {
        // Initialising main testing PremierLeagueManager.
        PremierLeagueManager plmMain = new PremierLeagueManager(2020);
        // Initialising testing PremierLeagueManager different year to main testing PremierLeagueManager.
        PremierLeagueManager plm2 = new PremierLeagueManager(2021);
        // Initialising testing PremierLeagueManager same year as main testing PremierLeagueManager.
        PremierLeagueManager plm3 = new PremierLeagueManager(2020);

        // Testing the hashCode method of PremierLeagueManager, using different year PremierLeagueManagers.
        assertNotEquals(plmMain.hashCode(), plm2.hashCode());
        // Testing the hashCode method of PremierLeagueManager, using same year PremierLeagueManagers.
        assertEquals(plmMain.hashCode(), plm3.hashCode());
    }

    /**
     * Method to test PremierLeagueManager.getCLUBS() method.
     */
    @Test
    @Order(1)
    void getCLUBS() {
        // Initialising testing PremierLeagueManager.
        PremierLeagueManager plm = new PremierLeagueManager(2020);
        // Testing if the return Data type of the getCLUBS() is correct.
        assertEquals(ArrayList.class, plm.getCLUBS().getClass());
    }

    /**
     * Method to test PremierLeagueManager.getMATCHES() method.
     */
    @Test
    @Order(2)
    void getMATCHES() {
        // Initialising testing PremierLeagueManager.
        PremierLeagueManager plm = new PremierLeagueManager(2020);
        // Testing if the return Data type of the getMatches() is correct.
        assertEquals(ArrayList.class, plm.getMATCHES().getClass());
    }

    /**
     * Method to test PremierLeagueManager.getYear() method.
     */
    @Test
    @Order(3)
    void getYear() {
        // Initialising testing PremierLeagueManager.
        PremierLeagueManager plm = new PremierLeagueManager(2020);
        // Testing if the getYear() matches works correctly.
        assertEquals(2020, plm.getYear());
    }

    /**
     * Method to test PremierLeagueManager.setYear() method.
     */
    @Test
    @Order(4)
    void setYear() {
        // Initialising testing PremierLeagueManager.
        PremierLeagueManager plm = new PremierLeagueManager();
        // Setting the year of PremierLeagueManager using setYear().
        plm.setYear(2020);
        // Testing if the setYear() works correctly.
        assertEquals(2020, plm.getYear());
    }
}