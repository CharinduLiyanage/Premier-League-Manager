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

package controllers;

// Imports.

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.FootballClub;
import entities.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.PremierLeagueManagerService;
import utils.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class fot the Premier League Manager.
 */
public class PremierLeagueManagerController extends Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger("controller");

    /**
     * Method to retrieve the league Table
     *
     * @param year The year of Premier League.
     * @return Returns a JSON file containing execution status and the result.
     */
    public Result listClubs(int year) {
        LOGGER.debug("In In PremierLeagueManagerController.listClubs(), retrieve PremierLeagueManager on year: {}", year);

        // Getting club list.
        List<FootballClub> result = PremierLeagueManagerService.getInstance().getClubList(year);

        LOGGER.debug("In PremierLeagueManagerController.listClubs(), result is: {}", result.toString());

        // Converting club list to JSON.
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);

        // Returning the Formatted Output.
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }

    /**
     * Method to retrieve all played matches.
     *
     * @param year The year of Premier League.
     * @return Returns a JSON file containing execution status and the result.
     */
    public Result listMatches(int year) {
        LOGGER.debug("In In PremierLeagueManagerController.listMatches(), retrieve PremierLeagueManager on year: {}", year);

        // Getting Match List.
        ArrayList<Match<FootballClub>> result = PremierLeagueManagerService.getInstance().getMatchList(year);

        LOGGER.debug("In PremierLeagueManagerController.listMatches(), result is: {}", result);

        // Converting match list to JSON.
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);

        // Returning the Formatted Output.
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }

    /**
     * Method to retrieve a randomly generated match.
     *
     * @param year The year of Premier League.
     * @return Returns a JSON file containing execution status and the result.
     */
    public Result retrieveRandomMatch(int year) {
        LOGGER.debug("In In PremierLeagueManagerController.retrieveRandomMatch(), retrieve PremierLeagueManager on year: {}", year);

        // Getting the random Match.
        Match<FootballClub> result = PremierLeagueManagerService.getInstance().getRandomMatch(year);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData;
        // When the Match is empty returning null.
        if (result.getDate() == null && result.getClubAway() == null && result.getClubHome() == null && result.getScoreAway() == 0 && result.getScoreHome() == 0) {
            jsonData = mapper.convertValue(null, JsonNode.class);
            LOGGER.debug("In PremierLeagueManagerController.retrieveRandomMatch(), result is: {}", (Object) null);
        }
        // When the match contains data.
        else {
            jsonData = mapper.convertValue(result, JsonNode.class);
            LOGGER.debug("In PremierLeagueManagerController.retrieveRandomMatch(), result is: {}", result.toString());
        }

        // Returning the Formatted Output.
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }

    /**
     * Method to add the Randomly generated match to the Premier League.
     *
     * @param year    The year of Premier League.
     * @param request The Match to be added in JSON format.
     * @return Returns a JSON file containing execution status and the result.
     */
    public Result createRandomMatch(int year, Http.Request request) {
        LOGGER.debug("In In PremierLeagueManagerController.createRandomMatch(), retrieve PremierLeagueManager on year: {}", year);

        // Loading the match data.
        JsonNode json = request.body().asJson();
        // When the http request is null
        if (json == null) {
            LOGGER.debug("In PremierLeagueManagerController.createRandomMatch(), match was not added, input is: {}", (Object) null);
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        LOGGER.debug("In PremierLeagueManagerController.createRandomMatch(), input is: {}", json.toString());

        // Making the http random match to Match object.
        Match<FootballClub> httpMatch = Json.fromJson(json, Match.class);
        // Adding Random Match to the PremierLeagueManager.
        Match<FootballClub> match = PremierLeagueManagerService.getInstance().addMatch(year, httpMatch);

        LOGGER.debug("In PremierLeagueManagerController.retrieveRandomMatch(), result is: {}", match);

        // Converting the added Match to JSON.
        JsonNode jsonObject = Json.toJson(match);

        // Returning the Formatted Output.
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

}
