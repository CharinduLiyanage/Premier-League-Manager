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
import utils.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class to Manage services done using PremierLeagueManager.
 * Adheres singleton principle.
 */
public class PremierLeagueManagerService {
    private static PremierLeagueManagerService instance;

    /**
     * Method to get the PremierLeagueManagerService instance.
     *
     * @return Returns PremierLeagueManagerService singleton object.
     */
    public static PremierLeagueManagerService getInstance() {
        // Creating an object if it is not being created yet.
        if (instance == null) {
            instance = new PremierLeagueManagerService();
        }
        // Return the Object.
        return instance;
    }

    /**
     * Method to load Premier League Manager from the save file.
     *
     * @param year Year of the Premier League to be loaded.
     * @return Returns the loaded Premier League.
     */
    private PremierLeagueManager getPLM(int year) {
        // Initializing the PremierLeagueManager.
        PremierLeagueManager plm = new PremierLeagueManager(year);

        // Loading the PremierLeagueManager.
        try {
            plm = Storage.load(year);
        } catch (IOException | ClassNotFoundException e) {
            // Creating a new save File if the save file is missing or corrupted.
            try {
                Storage.save(plm);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }

        // Returning the PremierLeagueManager.
        return plm;
    }

    /**
     * Method to get the League Table of a Premier League.
     *
     * @param year The Year of the Premier League.
     * @return ArrayList containing all clubs.
     */
    public ArrayList<FootballClub> getClubList(int year) {
        // Loading PremierLeagueManager.
        PremierLeagueManager plm = getPLM(year);
        // Returning the clubs ArrayList.
        return plm.getCLUBS();
    }

    /**
     * Method to get the all played matches of a Premier League.
     *
     * @param year The Year of the Premier League.
     * @return ArrayList containing all matches.
     */
    public ArrayList<Match<FootballClub>> getMatchList(int year) {
        // Loading PremierLeagueManager.
        PremierLeagueManager plm = getPLM(year);
        // Returning the matches ArrayList.
        return plm.getMATCHES();
    }

    /**
     * Method to get a randomly generated match.
     *
     * @param year Year of the Premier League.
     * @return Randomly generated match.
     */
    public Match<FootballClub> getRandomMatch(int year) {
        // Loading PremierLeagueManager.
        PremierLeagueManager plm = getPLM(year);

        // Initializing random Match.
        Match<FootballClub> match = new Match<>();
        // Checking if all the possible matches are played.
        if (plm.getMATCHES().size() < (plm.getCLUBS().size() - 1) * plm.getCLUBS().size()) {
            try {
                // Creating Random Date.
                Date date = randomDate(year);
                // Creating Random Home Club.
                FootballClub homeClub = randomHomeClub(date, year);
                // Creating Random Away Club.
                FootballClub awayClub = randomAwayClub(date, homeClub, year);

                // Updating Random Match Details.
                match = new Match<>(date, homeClub, awayClub);
            }
            // Home Club or Away Club or both being unavailable.
            catch (IllegalArgumentException e) {
                match = getRandomMatch(year);
            }
        }
        // Return randomly generated Match.
        return match;
    }

    /**
     * Method to add a match to Premier League.
     *
     * @param year  Year of the Premier League.
     * @param match Match to be added.
     * @return Added Match.
     */
    public Match<FootballClub> addMatch(int year, Match<FootballClub> match) {

        if (match.getDate() != null && match.getClubHome() != null && match.getClubAway() != null) {
            // Loading PremierLeagueManager.
            PremierLeagueManager plm = getPLM(year);
            // Adding the Match the PremierLeagueManager.
            boolean status = plm.addMatch(match);
            if (status) {
                // Saving the PremierLeagueManager.
                try {
                    Storage.save(plm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Returning the saved match.
                return match;
            }
        }
        return null;
    }

    /**
     * Method to select a random Away club for a random match.
     *
     * @param date     Date of the match.
     * @param homeClub Home club of the match.
     * @return Selected away club for the match.
     */
    private FootballClub randomAwayClub(Date date, FootballClub homeClub, int year) {
        Random random = new Random();

        // Loading PremierLeagueManager.
        PremierLeagueManager plm = getPLM(year);

        // Creating a clone of Club list to work with.
        ArrayList<FootballClub> clubs = (ArrayList<FootballClub>) plm.getCLUBS().clone();

        // Removing the randomly selected home club from possible clubs list.
        clubs.remove(homeClub);

        // Removing all the clubs that has matches on the randomly selected day.
        for (Match<FootballClub> match : plm.getMATCHES()) {
            if (match.getDate().equals(date)) {
                clubs.remove(match.getClubHome());
                clubs.remove(match.getClubAway());
            }
        }

        // Removing the clubs that have played all the possible matches as away club.
        for (Match<FootballClub> match : plm.getMATCHES()) {
            if (match.getClubHome().equals(homeClub)) {
                clubs.remove(match.getClubAway());
            }
        }

        // Selecting a Random away club.
        int randomClub = random.nextInt(clubs.size());

        // Returning the randomly selected away club.
        return clubs.get(randomClub);
    }

    /**
     * Method to select a random home club for a random match.
     *
     * @param date Date of the match.
     * @return Selected home club for the match.
     */
    private FootballClub randomHomeClub(Date date, int year) {
        Random random = new Random();

        // Loading PremierLeagueManager.
        PremierLeagueManager plm = getPLM(year);

        // Creating a clone of Club list to work with.
        ArrayList<FootballClub> clubs = (ArrayList<FootballClub>) plm.getCLUBS().clone();

        // Removing all the clubs that are already playing on the random date.
        for (Match<FootballClub> match : plm.getMATCHES()) {
            if (match.getDate().equals(date)) {
                clubs.remove(match.getClubAway());
                clubs.remove(match.getClubHome());
            }
        }

        // Removing clubs that has played with all the other clubs as a home club.
        for (FootballClub footballClub : clubs) {
            // Counting how many 'footballClub' has played as the home club.
            int matchCount = 0;
            for (Match<FootballClub> match : plm.getMATCHES()) {
                if (match.getClubHome().equals(footballClub)) {
                    matchCount++;
                }
            }
            // Removing the club from possible home club list if it has played all the possible matches.
            if (matchCount > (plm.getCLUBS().size() - 1) * 2) {
                clubs.remove(footballClub);
            }
        }

        // Generating the Random Home Club.
        int randomClub = random.nextInt(clubs.size());

        // Returning the selected Home Club.
        return clubs.get(randomClub);
    }

    /**
     * Method to select a random date for a random match.
     *
     * @return Selected date for the match.
     */
    private Date randomDate(int year) {
        Random random = new Random();

        // Loading PremierLeagueManager.
        PremierLeagueManager plm = getPLM(year);

        // Initialising Random Date.
        Date date = null;

        boolean isDateCreated = false;
        while (!isDateCreated) {
            // Generating random Month.
            int month = random.nextInt(12) + 1;
            // Deciding the Year according thr year.
            if (month < 6) {
                year += 1;
            }
            // Generating the Random day.
            int day = random.nextInt(dayMax(year, month));

            // Updating the random date.
            date = new Date(year, month, day);

            // Making sure one day can only hold 8 matches.
            if (getMatchDates(year).contains(date)) {
                int count = 0;
                boolean isDayFull = false;
                // Counting all the matches on the randomly generated date.
                for (Match<FootballClub> match : plm.getMATCHES()) {
                    if (match.getDate().equals(date)) {
                        count++;
                        // Marking the day as full if theres 8 or more matches on that day.
                        if (count >= 8) {
                            isDayFull = true;
                            break;
                        }
                    }
                }
                // adding a match day if the match day isn't full.
                if (!isDayFull) {
                    isDateCreated = true;
                }
            }
            // Adding the match to a new match day.
            else {
                isDateCreated = true;
            }
        }

        // Returning the Randomly generated match date.
        return date;
    }

    /**
     * Method to get the maximum number of days in a given month.
     *
     * @param year  The year.
     * @param month The month the number of days needed.
     * @return Returns the maximum number of days.
     */
    public int dayMax(int year, int month) {
        // Variable to hold the maximum number of days.
        int days;

        // February on a leap year.
        if (isLeapYear(year) && month == 2) {
            days = 29;
        }
        // February on normal year.
        else if (month == 2) {
            days = 28;
        }
        // Months with 30 days.
        else if ((month % 2 == 0 && month <= 7) || (month % 2 == 1 && month > 7)) {
            days = 30;
        }
        // Months with 31 days.
        else {
            days = 31;
        }

        // Returning the number of maximum days.
        return days;
    }

    /**
     * Method to check if the year is a leap year.
     *
     * @param year The year to be checked.
     * @return Returns true if the year is a leap year, false if not.
     */
    private boolean isLeapYear(int year) {
        // Initialising the variable to return.
        boolean leapYear = false;

        // If the year is divisible by 4.
        if (year % 4 == 0) {
            // checking if the year is divisible by 100.
            if (year % 100 == 0) {
                // Checking if the year is divisible by 400.
                if (year % 400 == 0) {
                    leapYear = true;
                }
            }
            // When year is divisible by 4 and not 100.
            else {
                leapYear = true;
            }
        }

        // Returning the output.
        return leapYear;
    }


    /**
     * Method to get all match days.
     *
     * @return Arraylist containing all match days.
     */
    private ArrayList<Date> getMatchDates(int year) {
        // Load Premier League manager.
        PremierLeagueManager plm = getPLM(year);

        // Initializing an ArrayList to store match-days.
        ArrayList<Date> dates = new ArrayList<>();

        // Adding match-days to the ArrayList.
        for (Match<FootballClub> match : plm.getMATCHES()) {
            boolean isNewMatchDay = true;
            // Checking if the day is already added.
            for (Date date : dates) {
                // Finding the same day in the Array.
                if (date.equals(match.getDate())) {
                    isNewMatchDay = false;
                    break;
                }
            }
            // Adding only if it is not already in the ArrayList.
            if (isNewMatchDay) {
                dates.add(match.getDate());
            }
        }

        // Sorting the match date ArrayList.
        dates.sort(Date::compareTo);

        // Returning the match-days ArrayList.
        return dates;
    }
}
