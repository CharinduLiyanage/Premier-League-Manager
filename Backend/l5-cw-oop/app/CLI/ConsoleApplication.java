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

package CLI;

// Imports.

import entities.*;
import services.PremierLeagueManagerService;
import utils.Storage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class that Runs the Premier League Manager in console.
 */
@SuppressWarnings("unchecked")
public class ConsoleApplication implements Runnable {

    // Final String containing a regex for all Strings that contain white spaces and english letters.
    private final static String TEXT_REGEX = "[a-zA-Z* ?]+";
    // Premier League Manager object.
    private static PremierLeagueManager plm = new PremierLeagueManager();

    /**
     * The main to run the Premier League Manager.
     *
     * @param args System arguments.
     */
    public static void main(String[] args) {
        // Showing initial welcome message.
        System.out.println("\n\n~ W E L C O M E   T O   P R E M I E R   L E A G U E   M A N A G E R ~");
        // Setting league year.
        setLeagueYear();

        // Loading Premier League details if if available.
        System.out.println("\nloading Primer League " + plm.getYear() + "...");
        loadPLM(false);

        // Running the menu.
        menu();
    }

    @Override
    public void run() {
        String[] args = new String[0];
        main(args);
    }

    /**
     * The method to make the menu for the Premier League Manager.
     */
    private static void menu() {
        // Initialising system scanner.
        Scanner sc = new Scanner(System.in);

        // Printing Menu options.
        System.out.println("\n\n-------------------------------------");
        System.out.println(" # # # # # #   M E N U   # # # # # # ");
        System.out.println("-------------------------------------");
        System.out.print("~ Select Option ~\n" +
                "\t1 : Add Club to League.\n" +
                "\t2 : Remove club from league.\n" +
                "\t3 : Add a match.\n" +
                "\t4 : Get stats of a club.\n" +
                "\t5 : Get league table.\n" +
                "\t6 : Save Premier League details.\n" +
                "\t7 : Open GUI of Premier League Manager.\n" +
                "\t8 : Quit Premier League Manager\n" +
                "\t\t: ");
        try {
            // Getting user input.
            int option = sc.nextInt();
            sc.nextLine();

            loadPLM(true);

            switch (option) {
                // User selected to add a club.
                case 1:
                    System.out.println("\nAdding a club...");
                    addingClub();
                    savePLM(plm, true);
                    break;
                // User selected to remove a club.
                case 2:
                    System.out.println("\nRemoving a club...");
                    removingClub();
                    savePLM(plm, true);
                    break;
                // User selected to add a match.
                case 3:
                    System.out.println("\nAdding a match...");
                    addingMatch();
                    savePLM(plm, true);
                    break;
                // User selected to get stats of a club.
                case 4:
                    System.out.println("\nGetting stats of a club...");
                    displayClubStats();
                    break;
                // User selected to get the Premier League table.
                case 5:
                    System.out.println("\nGetting league table...");
                    displayLeagueTable();
                    break;
                // User selected to save the Premier League details.
                case 6:
                    System.out.println("\nSaving Premier League...");
                    savePLM(plm, false);
                    break;
                // User selected to open GUI of Premier League Manager.
                case 7:
                    System.out.println("\nOpening GUI...");
                    // Checking if the default browser of the system is accessible.
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        try {
                            Desktop.getDesktop().browse(new URI("http://localhost:4200"));
                        }
                        // Exception handling when opening the browser.
                        catch (IOException | URISyntaxException e) {
                            System.out.println("\nERROR - Opening GUI Failed.\n\tOpen the browser manually and go to http://localhost:4200");
                        }
                    }
                    // Unsupported default browser of Java not having access.
                    else {
                        System.out.println("\nERROR - Opening GUI Failed.\n\tOpen the browser manually and go to http://localhost:4200");
                    }
                    break;
                // User selected to exit the Premier League Manager.
                case 8:
                    System.out.println("\nExiting Premier League Manager...");
                    System.exit(0);
                    // User enters an invalid option.
                default:
                    System.out.println("\nERROR - Invalid Option.");
                    break;
            }
            menu();
        }
        // User enters a non numeric character as an input.
        catch (InputMismatchException e) {
            System.out.println("\nERROR - Invalid Input.");
            menu();
        }
    }

    /**
     * Method to get user input for the year of the Premier League.
     */
    private static void setLeagueYear() {
        // Initialising system scanner.
        Scanner sc = new Scanner(System.in);

        // Prompting user to enter year.
        System.out.print("\n\tEnter League Year : ");
        try {
            // Getting user input.
            int year = sc.nextInt();
            sc.nextLine();

            // Checking if the year is within the supported range.
            if (year >= 1992 && year < 3000) {
                // Setting user Year.
                plm.setYear(year);
            }
            // User entering invalid year.
            else {
                System.out.println("\nERROR - Invalid Year(Enter a year between 1992 - 2999).");
                setLeagueYear();
            }
        }
        // User entering a non numerical character.
        catch (InputMismatchException e) {
            System.out.println("\nERROR - Invalid Input.");
            setLeagueYear();
        }
    }

    /**
     * Method to add a club to Premier League.
     */
    private static void addingClub() {
        // Initialising system scanner.
        Scanner sc = new Scanner(System.in);

        // Prompting user for club name.
        System.out.print("\n\tEnter Club name\t\t: ");
        // Getting the user input and making it into a default style.
        String name = toTitleCase(sc.nextLine());

        // Checking if the user input club name is blank.
        if (!name.isBlank()) {
            // check if the user input is containing only english letters and white spaces.
            if (name.matches(TEXT_REGEX)) {

                // Prompting user for club location.
                System.out.print("\tEnter Club location\t: ");
                // Getting the user input and making it into a default style.
                String location = toTitleCase(sc.nextLine());

                // Checking if the user input club location is blank.
                if (!location.isBlank()) {
                    // check if the user input is containing only english letters and white spaces.
                    if (location.matches(TEXT_REGEX)) {

                        // Making sure club is new to Premier league.
                        boolean isClubNew = true;
                        for (FootballClub club : plm.getCLUBS()) {
                            // Club already exist in Premier League.
                            if (club.getName().equalsIgnoreCase(name) && club.getLocation().equalsIgnoreCase(location)) {
                                isClubNew = false;
                                System.out.println("\nERROR - Club " + name + " from " + location + " is already in the Premier League " + plm.getYear() + ".");
                                break;
                            }
                        }

                        // Adding the club if it is new.
                        if (isClubNew) {
                            plm.addToClubList(new FootballClub(name, location));
                            System.out.println("\n\t# Club " + name + " from " + location + " added to the Premier League " + plm.getYear() + ".");
                        }
                    }
                    // Club location contains characters other than english letters.
                    else {
                        System.out.println("\nERROR - Club Location can only have English alphabetical characters.");
                    }
                }
                // Club location is blank.
                else {
                    System.out.println("\nERROR - Club Location cannot be blank.");
                }
            }
            // Club name contains characters other than english letters.
            else {
                System.out.println("\nERROR - Club Name can only have English alphabetical characters.");
            }
        }
        // Club name is blank.
        else {
            System.out.println("\nERROR - Club Name cannot be blank.");
        }
    }

    /**
     * Method to make Stings title case and remove redundant white space.
     *
     * @param text The String to be edited.
     * @return Returns a title case string.
     */
    private static String toTitleCase(String text) {
        // Removing white space from beginning and end.
        text = text.trim();

        // String builder for building the new String.
        StringBuilder converted = new StringBuilder();

        // Checking character by character to make title case.
        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            // If the character is a space.
            if (Character.isSpaceChar(ch)) {
                if (convertNext) {
                    continue;
                }
                convertNext = true;
            }
            // Start of a word.
            else if (convertNext) {
                ch = Character.toUpperCase(ch);
                convertNext = false;
            }
            // Middle letter of a word.
            else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        // Returning the title case string.
        return converted.toString();
    }

    /**
     * Method to remove clubs from Premier League.
     */
    private static void removingClub() throws InputMismatchException {
        // Initialising system scanner.
        Scanner sc = new Scanner(System.in);

        // Checking if there is any club to be removed.
        if (plm.getCLUBS().size() > 0) {
            // Prompting user to select a method to remove club.
            System.out.print("\nClub selection Method\n" +
                    "\t1 : To Remove a club using existing club list\n" +
                    "\t2 : To Remove using Club Name & Location\n" +
                    "\t\t: ");

            // Getting user input.
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                // User removing clubs using club list.
                case 1:
                    // Getting the club list alphabetical order.
                    ArrayList<FootballClub> clubs = clubListAsc();
                    System.out.println("\nSelected Club to be removed");
                    // Printing clubs.
                    for (int i = 0; i < clubs.size(); i++) {
                        System.out.println("\t" + (i + 1) + " : " + clubs.get(i).getName() + " From " + clubs.get(i).getLocation());
                    }
                    System.out.print("\t\t: ");
                    // Getting user input.
                    int optionClub = sc.nextInt();
                    sc.nextLine();

                    // Checking the user Input validity.
                    if (optionClub <= clubs.size() && optionClub > 0) {
                        // removing the club.
                        plm.deleteFromClubList(clubs.get(optionClub - 1).getName(), clubs.get(optionClub - 1).getLocation());
                        System.out.println("\n\t# Club " + clubs.get(optionClub - 1).getName() + " from " + clubs.get(optionClub - 1).getLocation() + " removed from Primer League " + plm.getYear() + ".");
                    }
                    // User enters an invalid option.
                    else {
                        System.out.println("\nERROR - Invalid Club Number.");
                    }
                    break;
                // User removing clubs by manually entering club name and location.
                case 2:
                    // Prompting user to enter club name.
                    System.out.print("\n\tEnter Club name\t\t: ");
                    // Getting user input club name and making it title case and remove redundant whitespace
                    String name = toTitleCase(sc.nextLine());
                    // Prompting user to enter club location.
                    System.out.print("\tEnter Club location\t: ");
                    // Getting user input club location and making it title case and remove redundant whitespace
                    String location = toTitleCase(sc.nextLine());

                    // Deleting the club.
                    boolean deleted = plm.deleteFromClubList(name, location);
                    // Printing success message if successfully deleted.
                    if (deleted) {
                        System.out.println("\n\t# Club " + name + " from " + location + " removed from Primer League " + plm.getYear() + ".");

                    }
                    // Club is not in the Premier League.
                    else {
                        System.out.println("\nERROR - Club " + name + " from " + location + " does not exist in Primer League " + plm.getYear() + ".");
                    }
                    break;
                // User entering a invalid option.
                default:
                    System.out.println("\nERROR - Invalid Option.");
            }
        }
        // There is no clubs in the Premier League.
        else {
            System.out.println("\nERROR - There are no clubs in Premier League " + plm.getYear() + ".");
        }
    }

    /**
     * Method to make the clubs list alphabetical.
     *
     * @return Returns a ArrayList containing FootballClubs in alphabetical order.
     */
    private static ArrayList<FootballClub> clubListAsc() {

        // Creating a clone of the FootballClub list in Premier League.
        ArrayList<FootballClub> clubs = (ArrayList<FootballClub>) plm.getCLUBS().clone();

        // Making it to alphabetical order.
        clubs.sort(SportsClub::compareTo);

        // Returning the sorted ArrayList.
        return clubs;
    }

    /**
     * Method to add matches.
     *
     * @throws InputMismatchException When user invalid an invalid input.
     */
    private static void addingMatch() throws InputMismatchException {
        // Initialising system scanner.
        Scanner sc = new Scanner(System.in);

        // Checking whether theres 2 clubs in Premier League to play a match.
        if (plm.getCLUBS().size() >= 2) {

            // Prompting user to input date for the match.
            System.out.print("\nDate of the match :\n" +
                    "\tYear\t: ");
            // Getting user input for the year.
            int year = sc.nextInt();
            sc.nextLine();

            // Checking the validity of the year.
            if (year == plm.getYear() || year == plm.getYear() + 1) {
                System.out.print("\tMonth\t: ");
                // Getting user input for the month.
                int month = sc.nextInt();
                sc.nextLine();

                // Validating the month.
                if (month > 0 && month < 13) {
                    System.out.print("\tDay\t\t: ");
                    // Getting user input for the day.
                    int day = sc.nextInt();
                    sc.nextLine();

                    // Validating the day.
                    if (day > 0 && day <= PremierLeagueManagerService.getInstance().dayMax(year, month)) {

                        // Prompting the user to select the.
                        System.out.print("\nClub selection Method\n" +
                                "\t1 : Add using Club List\n" +
                                "\t2 : Add using Club Name & Location\n" +
                                "\t\t: ");
                        int option = sc.nextInt();
                        sc.nextLine();

                        switch (option) {
                            // User selecting to add club using club list.
                            case 1:
                                // Getting the club list alphabetical order.
                                ArrayList<FootballClub> clubs = clubListAsc();
                                // Prompting user to select the home club and printing clubs.
                                System.out.println("\n\tSelected Home Club");
                                for (int i = 0; i < clubs.size(); i++) {
                                    System.out.println("\t\t" + (i + 1) + " : " + clubs.get(i).getName() + " from " + clubs.get(i).getLocation());
                                }
                                System.out.print("\t\t\t: ");
                                // Getting user input(Home Club).
                                int homeClubNo = sc.nextInt();
                                sc.nextLine();

                                // Checking the user Input validity(Home Club).
                                if (homeClubNo <= clubs.size() && homeClubNo > 0) {
                                    // Selecting the home club from the list.
                                    FootballClub homeClub = clubs.get(homeClubNo - 1);
                                    System.out.println("\n\t# Club " + homeClub.getName() + " from " + homeClub.getLocation() + " selected as Home Club.");
                                    // Removing the home club from the cloned club list.
                                    clubs.remove(homeClubNo - 1);

                                    // Prompting user to select the away club and printing clubs.
                                    System.out.println("\n\tSelected Away Club");
                                    for (int i = 0; i < clubs.size(); i++) {
                                        System.out.println("\t\t" + (i + 1) + " : " + clubs.get(i).getName() + " from " + clubs.get(i).getLocation());
                                    }
                                    System.out.print("\t\t\t: ");
                                    // Getting user input(Away Club).
                                    int awayClubNo = sc.nextInt();
                                    sc.nextLine();

                                    // Checking the user Input validity(Away Club).
                                    if (awayClubNo <= clubs.size() && awayClubNo > 0) {
                                        // Selecting the away club from the list.
                                        System.out.println("\n\t# Club " + clubs.get(awayClubNo - 1).getName() + " from " + clubs.get(awayClubNo - 1).getLocation() + " selected as Away Club.");

                                        // Getting user input for scores of the match.
                                        int[] scores = matchScores();

                                        // Adding/Updating the match
                                        updatingMATCHES(new Match<>(new Date(year, month, day), homeClub, clubs.get(awayClubNo - 1), scores[0], scores[1]));

                                    }
                                    // User inputs invalid away club number.
                                    else {
                                        System.out.println("\nERROR - Invalid Away Club Number.");
                                    }
                                }
                                // User inputs invalid home club number.
                                else {
                                    System.out.println("\nERROR - Invalid Home Club Number.");
                                }
                                break;
                            // User selecting to add club by typing club name and location.
                            case 2:
                                // Prompt the user to enter Home club name.
                                System.out.print("\n\tEnter Home Club Name\t\t: ");
                                // Take user input for Home club name and make it title case.
                                String homeClubName = toTitleCase(sc.nextLine());

                                // Prompt the user to enter Home club location.
                                System.out.print("\tEnter Home Club location\t: ");
                                // Take user input for Home club location and make it title case.
                                String homeClubLocation = toTitleCase(sc.nextLine());

                                // Declaring a variable to hold home club.
                                FootballClub homeClub = null;

                                // Checking if the user entered home club is there in the Premier League.
                                boolean isCorrectHomeClub = false;
                                for (FootballClub footballClub : clubListAsc()) {
                                    if (footballClub.getName().equalsIgnoreCase(homeClubName)
                                            && footballClub.getLocation().equalsIgnoreCase(homeClubLocation)) {
                                        isCorrectHomeClub = true;
                                        homeClub = footballClub;
                                        // Prompting the home club is selected.
                                        System.out.println("\n\t# Club " + homeClubName + " from " + homeClubLocation + " selected as Home Club.");
                                        break;
                                    }
                                }
                                // When home club is there in the Premier League.
                                if (isCorrectHomeClub) {
                                    // Prompt the user to enter away club name.
                                    System.out.print("\n\tEnter Away Club Name\t\t: ");
                                    // Take user input for away club name and make it title case.
                                    String awayClubName = toTitleCase(sc.nextLine());

                                    // Prompt the user to enter away club location.
                                    System.out.print("\tEnter Away Club location\t: ");
                                    // Take user input for away club location and make it title case.
                                    String awayClubLocation = toTitleCase(sc.nextLine());

                                    // Declaring a variable to hold away club.
                                    FootballClub awayClub = null;

                                    // Checking if the user entered away club is there in the Premier League.
                                    boolean isCorrectAwayClub = false;
                                    for (FootballClub footballClub : clubListAsc()) {
                                        if (footballClub.getName().equalsIgnoreCase(awayClubName)
                                                && footballClub.getLocation().equalsIgnoreCase(awayClubLocation)) {
                                            isCorrectAwayClub = true;
                                            awayClub = footballClub;
                                            break;
                                        }
                                    }
                                    // When away club is there in the Premier League.
                                    if (isCorrectAwayClub) {
                                        // Checking if the home club and away club is not the same.
                                        if (!homeClub.equals(awayClub)) {
                                            // Prompting the away club is selected.
                                            System.out.println("\n\t# Club " + awayClubName + " from " + awayClubLocation + " selected as Away Club.");

                                            // Getting match scores.
                                            int[] scores = matchScores();

                                            // Adding/Updating the match.
                                            updatingMATCHES(new Match<>(new Date(year, month, day), homeClub, awayClub, scores[0], scores[1]));
                                        }
                                        // When both away club and the home club are the same.
                                        else {
                                            System.out.println("\nERROR - Home Club and Away Club should be 2 different clubs.");
                                        }
                                    }
                                    // When the away club is not in the Premier League.
                                    else {
                                        System.out.println("\nERROR - Invalid Away Club.");
                                    }
                                }
                                // When the home club is not in the Premier League.
                                else {
                                    System.out.println("\nERROR - Invalid Home Club.");
                                }
                                break;
                            // User enters an invalid method to add clubs.
                            default:
                                System.out.println("\nERROR - Invalid Option.");
                                break;
                        }
                    }
                    // when invalid day is added.
                    else {
                        System.out.println("\nERROR - Invalid Day.");
                    }
                }
                // When invalid month is added.
                else {
                    System.out.println("\nERROR - Invalid Month.");
                }
            }
            // When invalid year is added.
            else {
                System.out.println("\nERROR - Premier League " + plm.getYear() + " Cannot happen in year " + year + ".");
            }
        }
        // When there are no 2 clubs in Premier League.
        else {
            System.out.println("\nERROR - There are no 2 clubs in the premier league to play a match.");
        }
    }

    /**
     * Method to add a new match or update an existing match.
     *
     * @param newMatch The match to be added/updated.
     */
    private static void updatingMATCHES(Match<FootballClub> newMatch) {
        // Initialising system scanner.
        Scanner sc = new Scanner(System.in);

        // Initialising the Main content of the status message.
        StringBuilder message = new StringBuilder("\n\t# Match between " + newMatch.getClubHome().getName() +
                " from " + newMatch.getClubHome().getLocation() + " & " +
                newMatch.getClubAway().getName() + " from " + newMatch.getClubAway().getLocation() +
                " on " + newMatch.getDate().getYear() + ":" +
                (newMatch.getDate().getMonth() < 10 ? "0" + newMatch.getDate().getMonth() : newMatch.getDate().getMonth()) +
                ":" + (newMatch.getDate().getDay() < 10 ? "0" + newMatch.getDate().getDay() : newMatch.getDate().getDay()));

        // Checking if the match is already there.
        boolean shouldAdd = true;
        boolean isNew = true;
        for (Match<FootballClub> oldMatch : plm.getMATCHES()) {
            if (newMatch.getClubHome().equals(oldMatch.getClubHome()) &&
                    newMatch.getClubAway().equals(oldMatch.getClubAway()) &&
                    oldMatch.getDate().equals(newMatch.getDate())) {
                // Asking user whether they  want to update the existing match.
                System.out.print("\n\tMatch is already in Premier League " + plm.getYear() + ", Do you want to update details (y/n) ?\n\t\t: ");
                // Getting user input.
                String option = sc.nextLine();

                // When user wants update the existing match.
                if (option.equalsIgnoreCase("y")) {
                    message.append(" updated.");
                    isNew = false;
                    break;
                }
                // When user wants to keep the existing match.
                else if (option.equalsIgnoreCase("n")) {
                    message.append(" not updated.");
                    isNew = false;
                    System.out.println(message);
                }
                // When user enters an invalid option.
                else {
                    System.out.println("\nERROR - Invalid Input.");
                }
                shouldAdd = false;
            }
        }

        // When the match should be added/updated.
        if (shouldAdd) {
            // When the match is new.
            if (isNew) message.append(" added.");
            // Adding/updating the match.
            plm.addMatch(newMatch);
            // Prompting status message.
            System.out.println(message);
        }
    }

    /**
     * Method to get user input for scores of a match.
     *
     * @return Returns the score in a, integer array.
     * @throws InputMismatchException When user enters an invalid input.
     */
    private static int[] matchScores() throws InputMismatchException {
        // Initialising system scanner.
        Scanner sc = new Scanner(System.in);

        // Variable to hold scores.
        int[] scores = {0, 0};

        // Prompting user to enter home club's score.
        System.out.print("\n\tEnter Home Club's Score : ");
        // Getting home club score.
        scores[0] = sc.nextInt();
        sc.nextLine();

        // Prompting user to enter away club's score.
        System.out.print("\n\tEnter Away Club's Score : ");
        // Getting away club score.
        scores[1] = sc.nextInt();
        sc.nextLine();

        // Returning the scores.
        return scores;
    }

    /**
     * Method to display stats of a club.
     */
    private static void displayClubStats() {
        // Initialising system scanner.
        Scanner sc = new Scanner(System.in);

        // Checking if there is any clubs in Premier League.
        if (plm.getCLUBS().size() > 0) {
            // Prompting user to enter club name.
            System.out.print("\n\tEnter the club name\t\t: ");
            // Making the club name title case and remove redundant whitespace.
            String name = toTitleCase(sc.nextLine());

            // Prompting user to enter club location.
            System.out.print("\tEnter the club location\t: ");
            // Making the club location title case and remove redundant whitespace.
            String location = toTitleCase(sc.nextLine());

            // Finding the club from Premier League club list.
            boolean isClubFound = false;
            for (FootballClub footballClub : plm.getCLUBS()) {
                if (footballClub.getName().equalsIgnoreCase(name) && footballClub.getLocation().equalsIgnoreCase(location)) {
                    isClubFound = true;
                    // Removing the club from Premier League.
                    System.out.println("\n" + plm.displayClub(footballClub));
                    break;
                }
            }
            // When the club is not there in Premier League.
            if (!isClubFound) {
                System.out.println("\nERROR - Club " + name + " from " + location + " does not exist in Primer League " + plm.getYear() + ".");
            }
        }
        // When there are no clubs in Premier League to remove.
        else {
            System.out.println("\nERROR - There are no clubs in Premier League " + plm.getYear() + ".");
        }
    }

    /**
     * Method to display league table.
     */
    private static void displayLeagueTable() {
        // Checking if there are any clubs in the Premier League.
        if (plm.getCLUBS().size() > 0) {
            // Displaying the Premier League table.
            System.out.println("\n" + plm.displayLeagueTable());
        }
        // When there are no clubs in Premier League.
        else {
            System.out.println("\nERROR - There are no clubs in Premier League " + plm.getYear() + ".");
        }
    }

    /**
     * Method to save Premier League.
     *
     * @param plm PremierLeagueManager object to be saved.
     */
    private static void savePLM(PremierLeagueManager plm, boolean isAutomaticSaving) {
        try {

            Storage.save(plm);

            // Prompting the successfully saved message.
            if (!isAutomaticSaving) System.out.println("# Successfully Saved.");
        }
        // Error occurring while saving.
        catch (IOException e) {
            if (!isAutomaticSaving) System.out.println("\nERROR - Saving was Interrupted.");
        }
    }

    /**
     * Method to load save data from text file.
     */
    private static void loadPLM(boolean isAutomaticLoading) {
        try {

            setPlm(Storage.load(plm.getYear()));
//            plm = Storage.loadPLM(plm.getYear());

            // Prompting the successfully loaded message.
            if (!isAutomaticLoading) System.out.println("# Successfully Loaded.");
        }
        // When there is no save file.
        catch (FileNotFoundException e) {
            if (!isAutomaticLoading) {
                System.out.println("\nWARNING - No pre-existing data.");
                savePLM(plm, true);
            }
        }
        // when the save file is corrupted.
        catch (Exception anyOtherError) {
            if (!isAutomaticLoading) {
                System.out.println("\nERROR - Save data is Corrupted.");
                savePLM(plm, true);
            }
        }
    }

    /**
     * Getter method for the TEXT_REGEX.
     *
     * @return Returns the TEXT_REGEX.
     */
    public static String getTextRegex() {
        return TEXT_REGEX;
    }

    /**
     * Getter method for the PremierLeagueManager attribute.
     *
     * @return Returns the PremierLeagueManager attribute.
     */
    public static PremierLeagueManager getPlm() {
        return plm;
    }

    /**
     * Setter method for the PremierLeagueManager attribute.
     *
     * @param plm The PremierLeagueManager attribute.
     */
    public static void setPlm(PremierLeagueManager plm) {
        ConsoleApplication.plm = plm;
    }
}
