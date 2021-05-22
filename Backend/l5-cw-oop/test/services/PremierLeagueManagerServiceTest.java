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

package services;

// Imports.

import entities.Date;
import entities.FootballClub;
import entities.Match;
import entities.PremierLeagueManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Storage;

import java.io.IOException;

/**
 * Test Class to test PremierLeagueManagerService class.
 */
class PremierLeagueManagerServiceTest {
    private int testYear1;  // Year of the test PremierLeagueManager.
    private PremierLeagueManager testPLM1;  // test PremierLeagueManager.
    private int testYear2;  // Year of the test PremierLeagueManager.
    private PremierLeagueManager testPLM2;  // test PremierLeagueManager.
    private FootballClub footballClubA;  // test FootballCLub.
    private FootballClub footballClubB;  // test FootballCLub.

    /**
     * Method to initialize all the tests.
     */
    @BeforeEach
    void initializingPLM() {
        // Setting the year of the test PremierLeagueManager.
        testYear1 = 400;
        // Initializing the PremierLeagueManager for testing.
        testPLM1 = new PremierLeagueManager(testYear1);

        footballClubA = new FootballClub("name a", "location a");
        footballClubB = new FootballClub("name b", "location c");
        testPLM1.addToClubList(footballClubA);
        testPLM1.addToClubList(footballClubB);
        testPLM1.addMatch(new Match<>(new Date(testYear1, 5, 6), footballClubA, footballClubB));

        // Setting the year of the test PremierLeagueManager.
        testYear2 = 401;
        // Initializing the PremierLeagueManager for testing.
        testPLM2 = new PremierLeagueManager(testYear2);

        // Saving the initialised PremierLeagueManager.
        try {
            Storage.save(testPLM1);
            Storage.save(testPLM2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to test PremierLeagueManagerService.getInstance() method.
     */
    @Test
    void getInstance() {
        // Testing the instance method outputs a PremierLeagueManagerService class object.
        Assertions.assertEquals(PremierLeagueManagerService.class, PremierLeagueManagerService.getInstance().getClass());
    }

    /**
     * Method to test PremierLeagueManagerService.getClubList() method.
     */
    @Test
    void getClubList() {
        // Testing for a PremierLeagueManager with clubs and matches.
        Assertions.assertEquals(testPLM1.getCLUBS(), PremierLeagueManagerService.getInstance().getClubList(testYear1));
        // Testing for a PremierLeagueManager without clubs or matches.
        Assertions.assertEquals(testPLM2.getCLUBS(), PremierLeagueManagerService.getInstance().getClubList(testYear2));
    }

    /**
     * Method to test PremierLeagueManagerService.getMatchList() method.
     */
    @Test
    void getMatchList() {
        // Testing for a PremierLeagueManager with clubs and matches.
        Assertions.assertEquals(testPLM1.getMATCHES(), PremierLeagueManagerService.getInstance().getMatchList(testYear1));
        // Testing for a PremierLeagueManager without clubs or matches.
        Assertions.assertEquals(testPLM2.getMATCHES(), PremierLeagueManagerService.getInstance().getMatchList(testYear2));
    }

    /**
     * Method to test PremierLeagueManagerService.getRandomMatch() method.
     */
    @Test
    void getRandomMatch() {
        // Testing for a PremierLeagueManager with clubs and matches.
        Assertions.assertEquals(Match.class, PremierLeagueManagerService.getInstance().getRandomMatch(testPLM1.getYear()).getClass());
        Match<FootballClub> match = PremierLeagueManagerService.getInstance().getRandomMatch(testPLM1.getYear());
        // This test is possible because this is the only match that can be generated.
        Assertions.assertEquals(footballClubB, match.getClubHome());
        Assertions.assertEquals(footballClubA, match.getClubAway());

        // Testing for a PremierLeagueManager without clubs or matches.
        Assertions.assertEquals(Match.class, PremierLeagueManagerService.getInstance().getRandomMatch(testPLM2.getYear()).getClass());
        boolean isNewMatchPossible = testPLM2.getMATCHES().size() < (testPLM2.getCLUBS().size() - 1) * testPLM2.getCLUBS().size();
        if (!isNewMatchPossible) {
            match = PremierLeagueManagerService.getInstance().getRandomMatch(testPLM2.getYear());
            Assertions.assertNull(match.getDate());
            Assertions.assertNull(match.getClubHome());
            Assertions.assertNull(match.getClubHome());
        }
    }

    /**
     * Method to test PremierLeagueManagerService.addMatch() method.
     */
    @Test
    void addMatch() {
        // Testing for a PremierLeagueManager with clubs and matches.
        Match<FootballClub> randomMatch = PremierLeagueManagerService.getInstance().getRandomMatch(testYear1);
        Match<FootballClub> addedMatch = PremierLeagueManagerService.getInstance().addMatch(testYear1, randomMatch);
        Assertions.assertEquals(randomMatch, addedMatch);

        // After the first match added there shouldn't be another possible match to add.
        randomMatch = PremierLeagueManagerService.getInstance().getRandomMatch(testYear1);
        addedMatch = PremierLeagueManagerService.getInstance().addMatch(testYear1, randomMatch);
        Assertions.assertNull(addedMatch);

        // Testing for a PremierLeagueManager without clubs or matches.
        randomMatch = PremierLeagueManagerService.getInstance().getRandomMatch(testYear2);
        addedMatch = PremierLeagueManagerService.getInstance().addMatch(testYear2, randomMatch);
        Assertions.assertNull(addedMatch);
    }

    /**
     * Method to test PremierLeagueManagerService.dayMax() method.
     */
    @Test
    void dayMax() {
        // Max no of day for months in any common year in ascending order of months.
        int[] daysCommonYear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // Max no of day for months in any leap year in ascending order of months.
        int[] daysLeapYear = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


        // Testing for a Common Year.
        for (int i = 0; i < daysCommonYear.length; i++) {
            Assertions.assertEquals(daysCommonYear[i], PremierLeagueManagerService.getInstance().dayMax(2000, i + 1));
        }

        // Testing for a Leap Year.
        for (int i = 0; i < daysLeapYear.length; i++) {
            Assertions.assertEquals(daysLeapYear[i], PremierLeagueManagerService.getInstance().dayMax(2001, i + 1));
        }
    }
}