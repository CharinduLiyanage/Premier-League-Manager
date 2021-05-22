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
import java.util.ArrayList;
import java.util.Objects;

/**
 * The PremierLeagueManager class to make PremierLeagueManager objects.
 */
public class PremierLeagueManager implements LeagueManager<FootballClub>, Serializable {
    private final ArrayList<FootballClub> CLUBS = new ArrayList<>();
    private final ArrayList<Match<FootballClub>> MATCHES = new ArrayList<>();
    private int year;

    /**
     * Default Constructor for PremierLeagueManager.
     */
    public PremierLeagueManager() {

    }

    /**
     * An argument constructor for PremierLeagueManager.
     *
     * @param year Year of the PremierLeagueManager object.
     */
    public PremierLeagueManager(int year) {
        this.year = year;
    }

    /**
     * Method to add a match to the MATCHES arraylist.
     *
     * @param sportsClub FootballClub to be added.
     */
    @Override
    public void addToClubList(FootballClub sportsClub) {
        // Making sure the location and the name of the club is not null.
        if (sportsClub.getName() == null || sportsClub.getLocation() == null) return;

        // Adding a new Club to the CLUBS list.
        CLUBS.add(sportsClub);
    }

    /**
     * Method to remove club from the CLUBS arraylist.
     *
     * @param clubName Name of the club to be removed.
     * @param location Location of the Club to be removed.
     * @return Returns a boolean, status of removal ('true' if removed, 'false' if not removed).
     */
    @Override
    public boolean deleteFromClubList(String clubName, String location) {
        // Removing the Club form the CLUBS list if club is there in the CLUBS list, returning status.
        return CLUBS.removeIf(sportsClub -> sportsClub.getName().equals(clubName)
                && sportsClub.getLocation().equals(location));
    }


    /**
     * Method to add a match to the MATCHES arraylist.
     *
     * @param match Match to be added.
     */
    @Override
    public boolean addMatch(Match<FootballClub> match) {
        if (match.getDate() != null && match.getClubHome() != null && match.getClubAway() != null) {
            FootballClub homeClub = null;
            FootballClub awayClub = null;

            for (FootballClub club : CLUBS) {
                if (match.getClubHome().getName().equals(club.getName()) &&
                        match.getClubHome().getLocation().equals(club.getLocation())) {
                    homeClub = club;
                }
                if (match.getClubAway().getName().equals(club.getName()) &&
                        match.getClubAway().getLocation().equals(club.getLocation())) {
                    awayClub = club;
                }
                if (homeClub != null && awayClub != null) {
                    break;
                }
            }

            if (homeClub != null && awayClub != null) {
                // Checking if the match is already in the Premier League.
                boolean isNew = true;
                for (int i = 0; i < MATCHES.size(); i++) {
                    // Already existing match.
                    Match<FootballClub> oldMatch = MATCHES.get(i);

                    if (match.getClubHome().equals(oldMatch.getClubHome()) &&
                            match.getClubAway().equals(oldMatch.getClubAway()) &&
                            oldMatch.getDate().equals(match.getDate())) {

                        isNew = false;

                        // Removing the changes old match made to the FootballClubs
                        // Checking if the old match is a draw.
                        if (oldMatch.isDraw()) {
                            // Updating home club & away club draws.
                            homeClub.setDraws(homeClub.getDraws() - 1);
                            awayClub.setDraws(awayClub.getDraws() - 1);
                        }
                        // Home club wining the match.
                        else if (oldMatch.getScoreHome() > oldMatch.getScoreAway()) {
                            // Updating home club's win.
                            homeClub.setWins(homeClub.getWins() - 1);
                            // Updating away club's loss.
                            awayClub.setLosses(awayClub.getLosses() - 1);
                        }
                        // Away club wining the match.
                        else {
                            // Updating home club's win.
                            awayClub.setWins(awayClub.getWins() - 1);
                            // Updating away club's loss.
                            homeClub.setLosses(homeClub.getLosses() - 1);

                        }

                        // Updating Goals-For and Goals-Against home club.
                        homeClub.setGoalsFor(homeClub.getGoalsFor() - oldMatch.getScoreHome());
                        homeClub.setGoalsAgainst(homeClub.getGoalsAgainst() - oldMatch.getScoreAway());

                        // Updating Goals-For and Goals-Against away club.
                        awayClub.setGoalsFor(awayClub.getGoalsFor() - oldMatch.getScoreAway());
                        awayClub.setGoalsAgainst(awayClub.getGoalsAgainst() - oldMatch.getScoreHome());

                        // Replacing the old match with the new match.
                        MATCHES.set(i, match);
                    }
                }

                // Checking if the match is a draw.
                if (match.isDraw()) {
                    // Updating home club & away club draws.
                    homeClub.setDraws(homeClub.getDraws() + 1);
                    awayClub.setDraws(awayClub.getDraws() + 1);
                }
                // Home club wining the match.
                else if (match.getScoreHome() > match.getScoreAway()) {
                    // Updating home club's win.
                    homeClub.setWins(homeClub.getWins() + 1);
                    // Updating away club's loss.
                    awayClub.setLosses(awayClub.getLosses() + 1);
                }
                // Away club wining the match.
                else {
                    // Updating home club's win.
                    awayClub.setWins(awayClub.getWins() + 1);
                    // Updating away club's loss.
                    homeClub.setLosses(homeClub.getLosses() + 1);
                }

                // Updating Goals-For and Goals-Against home club.
                homeClub.setGoalsFor(homeClub.getGoalsFor() + match.getScoreHome());
                homeClub.setGoalsAgainst(homeClub.getGoalsAgainst() + match.getScoreAway());

                // Updating Goals-For and Goals-Against away club.
                awayClub.setGoalsFor(awayClub.getGoalsFor() + match.getScoreAway());
                awayClub.setGoalsAgainst(awayClub.getGoalsAgainst() + match.getScoreHome());

                if (isNew) {
                    // Adding a new match to the MATCHES list.
                    MATCHES.add(match);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Method to display details of a selected club.
     *
     * @param footballClub FootballClub to be displayed.
     * @return Returns a String containing the details.
     */
    @Override
    public String displayClub(FootballClub footballClub) {
        // Finding the Maximum character length that is there in the FootBallClub details.
        int charLenMax = Integer.max(Integer.toString(footballClub.getPoints()).length(),
                Integer.max(Integer.toString(footballClub.getGoalsFor()).length(),
                        Integer.max(Integer.toString(footballClub.getGoalsAgainst()).length(),
                                Integer.max(Integer.toString(footballClub.getLosses()).length(),
                                        Integer.max(Integer.toString(footballClub.getDraws()).length(),
                                                Integer.max(Integer.toString(footballClub.getWins()).length(),
                                                        Integer.max(Integer.toString(footballClub.getMatchesPlayed()).length(),
                                                                Integer.max(footballClub.getName().length(),
                                                                        footballClub.getLocation().length()
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        // Creating the stats display String.
        String str = "";
        str += "\t|" + "-".repeat(24 + charLenMax) + "|";
        str += "\n\t| # Club            :  " + footballClub.getName() + " ".repeat(2 + charLenMax - footballClub.getName().length()) + "|";
        str += "\n\t| # location        :  " + footballClub.getLocation() + " ".repeat(2 + charLenMax - footballClub.getLocation().length()) + "|";
        str += "\n\t| # Matches Played  :  " + footballClub.getMatchesPlayed() + " ".repeat(2 + charLenMax - Integer.toString(footballClub.getMatchesPlayed()).length()) + "|";
        str += "\n\t| # Wins            :  " + footballClub.getWins() + " ".repeat(2 + charLenMax - Integer.toString(footballClub.getWins()).length()) + "|";
        str += "\n\t| # Draws           :  " + footballClub.getDraws() + " ".repeat(2 + charLenMax - Integer.toString(footballClub.getDraws()).length()) + "|";
        str += "\n\t| # Losses          :  " + footballClub.getLosses() + " ".repeat(2 + charLenMax - Integer.toString(footballClub.getLosses()).length()) + "|";
        str += "\n\t| # Goals For       :  " + footballClub.getGoalsAgainst() + " ".repeat(2 + charLenMax - Integer.toString(footballClub.getGoalsAgainst()).length()) + "|";
        str += "\n\t| # Goals Against   :  " + footballClub.getGoalsFor() + " ".repeat(2 + charLenMax - Integer.toString(footballClub.getGoalsFor()).length()) + "|";
        str += "\n\t| # Points          :  " + footballClub.getPoints() + " ".repeat(2 + charLenMax - Integer.toString(footballClub.getPoints()).length()) + "|";
        str += "\n\t|" + "-".repeat(24 + charLenMax) + "|";

        return str;
    }

    /**
     * Method to display the league table.
     *
     * @return Returns the String table containing the details of the league.
     */
    @Override
    public String displayLeagueTable() {
        // Making a Clone of CLUBS list to make the display.
        ArrayList<FootballClub> clubs = (ArrayList<FootballClub>) CLUBS.clone();
        // Sorting the list.
        clubs.sort(FootballClub::compareTo);

        // Initializing lengths of the column headers' for the max length for columns .
        int nameLen = "Club".length(),
                matchesPlayedMax = "Matches Played".length(),
                winsMax = "Wins".length(),
                drawsMax = "Draws".length(),
                lossesMax = "Losses".length(),
                goalsForMax = "Goals For".length(),
                goalsAgainstMax = "Goals Against".length(),
                goalDifferenceMax = "Goal Difference".length(),
                pointsMax = "Points".length();

        // Finding lengths of details of the clubs in the list and updating the max length,
        for (FootballClub footballClub : clubs) {
            if (footballClub.getName().length() > nameLen) {
                nameLen = footballClub.getName().length();
            }
            matchesPlayedMax = Integer.max(matchesPlayedMax, Integer.toString(footballClub.getMatchesPlayed()).length());
            winsMax = Integer.max(winsMax, Integer.toString(footballClub.getWins()).length());
            drawsMax = Integer.max(drawsMax, Integer.toString(footballClub.getDraws()).length());
            lossesMax = Integer.max(lossesMax, Integer.toString(footballClub.getLosses()).length());
            goalDifferenceMax = Integer.max(goalDifferenceMax, Integer.toString(footballClub.getGoalDifference()).length());
            goalsForMax = Integer.max(goalsForMax, Integer.toString(footballClub.getGoalsFor()).length());
            goalsAgainstMax = Integer.max(goalsAgainstMax, Integer.toString(footballClub.getGoalsAgainst()).length());
            pointsMax = Integer.max(pointsMax, Integer.toString(footballClub.getPoints()).length());
        }

        // Making the Header Column for the display.
        StringBuilder str = new StringBuilder();
        str.append(rowBreak(nameLen, matchesPlayedMax, winsMax, drawsMax, lossesMax, goalsForMax, goalsAgainstMax, goalDifferenceMax, pointsMax, 19));
        str.append(rowString("Club", nameLen));
        str.append(rowString("Matches Played", matchesPlayedMax));
        str.append(rowString("Wins", winsMax));
        str.append(rowString("Draws", drawsMax));
        str.append(rowString("Losses", lossesMax));
        str.append(rowString("Goals For", goalsForMax));
        str.append(rowString("Goals Against", goalsAgainstMax));
        str.append(rowString("Goal Difference", goalDifferenceMax));
        str.append(rowString("Points", pointsMax));
        str.append(rowString("Last 5 Matches", 19));
        str.append("|\n");
        str.append(rowBreak(nameLen, matchesPlayedMax, winsMax, drawsMax, lossesMax, goalsForMax, goalsAgainstMax, goalDifferenceMax, pointsMax, 0));

        // Making the Body of the display table.
        for (FootballClub footballClub : clubs) {
            str.append(rowString(footballClub.getName(), nameLen));
            str.append(rowString(Integer.toString(footballClub.getMatchesPlayed()), matchesPlayedMax));
            str.append(rowString(Integer.toString(footballClub.getWins()), winsMax));
            str.append(rowString(Integer.toString(footballClub.getDraws()), drawsMax));
            str.append(rowString(Integer.toString(footballClub.getLosses()), lossesMax));
            str.append(rowString(Integer.toString(footballClub.getGoalsFor()), goalsForMax));
            str.append(rowString(Integer.toString(footballClub.getGoalsAgainst()), goalsAgainstMax));
            str.append(rowString(Integer.toString(footballClub.getGoalDifference()), goalDifferenceMax));
            str.append(rowString(Integer.toString(footballClub.getPoints()), pointsMax));
            str.append(last5Matches(footballClub));
            str.append("\n");
            str.append(rowBreak(nameLen, matchesPlayedMax, winsMax, drawsMax, lossesMax, goalsForMax, goalsAgainstMax, goalDifferenceMax, pointsMax, 0));
        }
        str = new StringBuilder(str.toString().trim());

        // Returning the Display table.
        return str.toString();
    }

    /**
     * Method to make the last five matches stat.
     *
     * @param club FootBallClub to check last five matches.
     * @return Returns a String displaying sats of last five matches.
     */
    private String last5Matches(FootballClub club) {
        StringBuilder str = new StringBuilder("||");

        ArrayList<Match<FootballClub>> matches = (ArrayList<Match<FootballClub>>) MATCHES.clone();
        matches.removeIf(match -> (!(match.getClubAway().equals(club) || match.getClubHome().equals(club))));
        matches.sort(Match::compareTo);

        int j = 5;

        // Checking if the club has played les than 5 matches.
        if (matches.size() <= 5) {
            j = matches.size();
            str.append(" - |".repeat(5 - matches.size()));
        }

        // Adding status for played matches.
        for (int i = 0; i < j; i++) {
            Match<FootballClub> match;
            if (matches.size() <= 5) {
                match = matches.get(i);
            } else {
                match = matches.get(matches.size() - 5 + i);
            }

            // Match being a draw.
            if (match.isDraw()) {
                str.append(" D |");
            }
            // Club winning the match
            else if ((match.getClubHome().equals(club) && match.getScoreHome() > match.getScoreAway()) ||
                    (match.getClubAway().equals(club) && match.getScoreAway() > match.getScoreHome())) {
                str.append(" W |");
            }
            // Club loosing the match.
            else {
                str.append(" L |");
            }
        }

        str.append("| ");

        return str.toString();
    }

    /**
     * Method to make row breaks for the display league table.
     *
     * @param column1 Width of the column 1.
     * @param column2 Width of the column 2.
     * @param column3 Width of the column 3.
     * @param column4 Width of the column 4.
     * @param column5 Width of the column 5.
     * @param column6 Width of the column 6.
     * @param column7 Width of the column 7.
     * @param column8 Width of the column 8.
     * @param column9 Width of the column 9.
     * @return Returns the row break line as a String.
     */
    private String rowBreak(int column1, int column2, int column3, int column4, int column5, int column6, int column7, int column8, int column9, int column10) {

        // Creating the Column break using the cell item creating method.
        String str = "";
        str += rowString("-", column1);
        str += rowString("-", column2);
        str += rowString("-", column3);
        str += rowString("-", column4);
        str += rowString("-", column5);
        str += rowString("-", column6);
        str += rowString("-", column7);
        str += rowString("-", column8);
        str += rowString("-", column9);
        if (column10 == 0) {
            str += "|+---+---+---+---+---+";
        } else {
            str += rowString("-", column10);
        }
        str += "|\n";

        // Replacing spaces with dashes.
        str = str.replace(" ", "-");

        return str;
    }

    /**
     * Method to make the row items for the league table.
     *
     * @param item            The item for the cell.
     * @param columnMaxLength The number of characters that wil be in that column.
     * @return String of a cell item.
     */
    private String rowString(String item, int columnMaxLength) {
        boolean isNumeric = item.matches("-?\\d+(\\.\\d+)?");
        // Calculating the number of padding needed.
        int padding = columnMaxLength - item.length();

        String repeat1 = " ";
        String repeat2 = " ";

        // Checking if its the max length detail.
        if (padding != 0) {
            // Checking if the item is a number.
            if (isNumeric) {
                repeat1 = repeat1.repeat(padding + 1);
            } else {
                repeat2 = repeat2.repeat(padding + 1);
            }
        }
        return "|" + repeat1 + item + repeat2;
    }

    /**
     * The Method to make a PremierLeagueManager object a String.
     *
     * @return Returns a String containing all the details of the PremierLeagueManager object.
     */
    @Override
    public String toString() {
        return "PremierLeagueManager{" +
                "CLUBS=" + CLUBS +
                ", MATCHES=" + MATCHES +
                ", year=" + year +
                '}';
    }

    /**
     * The method used for comparing two PremierLeagueManager objects.
     *
     * @param that FootballClub object to compare with.
     * @return Returns an Integer ('0' if equal in value, '-1' if lesser, '1' if greater).
     */
    public int compareTo(PremierLeagueManager that) {
        return Integer.compare(year, that.getYear());
    }

    /**
     * The Method used to check if two PremierLeagueManager objects are equal.
     *
     * @param o PremierLeagueManager object to check whether equal.
     * @return Returns a boolean value ('true' if equal, 'false' if unequal).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PremierLeagueManager that = (PremierLeagueManager) o;
        return CLUBS.equals(that.CLUBS) &&
                MATCHES.equals(that.MATCHES) &&
                Objects.equals(year, that.year);
    }

    /**
     * The hashCode method for PremierLeagueManager object.
     *
     * @return Returns an unique integer value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(CLUBS, MATCHES, year);
    }

    /**
     * Getter method for the CLUBS list of the PremierLeagueManager object.
     *
     * @return Returns the CLUBS list of the PremierLeagueManager object.
     */
    public ArrayList<FootballClub> getCLUBS() {
        return CLUBS;
    }

    /**
     * Getter method for the MATCHES list of the PremierLeagueManager object.
     *
     * @return Returns the MATCHES list of the PremierLeagueManager object.
     */
    public ArrayList<Match<FootballClub>> getMATCHES() {
        return MATCHES;
    }

    /**
     * Getter method for the year of the PremierLeagueManager object.
     *
     * @return Returns the year of the PremierLeagueManager object.
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter method for the year of the PremierLeagueManager object.
     *
     * @param year The year of the PremierLeagueManager object.
     */
    public void setYear(int year) {
        this.year = year;
    }

}
