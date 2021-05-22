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

import akka.util.ByteString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.ConfigFactory;
import entities.Date;
import entities.FootballClub;
import entities.Match;
import entities.PremierLeagueManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import play.Application;
import play.http.HttpEntity;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import services.PremierLeagueManagerService;
import utils.Storage;

import java.io.IOException;
import java.util.ArrayList;

import static play.mvc.Http.Status.CREATED;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

/**
 * Test Class to test PremierLeagueManagerController class.
 */
class PremierLeagueManagerControllerTest {

    int testYear;  // Year of the test PremierLeagueManager.
    Application application;  // Test Application.
    PremierLeagueManager testPLM;  // Test PremierLeagueManager.

    /**
     * Method to initialize all the tests.
     */
    @BeforeEach
    void initialization() {
        // Setting testing PremierLeagueManager year.
        testYear = 200;

        // Initializing PremierLeagueManager.
        testPLM = new PremierLeagueManager(testYear);

        // Creating and Adding clubs to the PremierLeagueManager.
        FootballClub testFootballClubA = new FootballClub("Name A", "Location A");
        testPLM.addToClubList(testFootballClubA);
        FootballClub testFootballClubB = new FootballClub("Name B", "Location B");
        testPLM.addToClubList(testFootballClubB);
        FootballClub testFootballClubC = new FootballClub("Name C", "Location C");
        testPLM.addToClubList(testFootballClubC);
        FootballClub testFootballClubD = new FootballClub("Name D", "Location D");
        testPLM.addToClubList(testFootballClubD);

        // Adding a match to the PremierLeagueManager.
        testPLM.addMatch(new Match<>(new Date(testYear, 6, 6), testFootballClubA, testFootballClubB));

        // Saving the PremierLeagueManager.
        try {
            Storage.save(testPLM);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialising the Application.
        application = new GuiceApplicationBuilder()
                .withConfigLoader(env -> ConfigFactory.load(env.classLoader()))
                .build();
        // Starting the Application.
        Helpers.start(application);
    }

    /**
     * Method to test PremierLeagueManagerController.listClubs() method.
     */
    @Test
    void listClubs() {
        // Building Get request for PremierLeagueManagerController.listClubs() .
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri(routes.PremierLeagueManagerController.listClubs(testYear).url());

        // Getting the result.
        Result result = route(application, request);


        // Testing if the result is equal to the expected result.
        Assertions.assertEquals(OK, result.status());

        // Getting the body of the http.Entity.
        ByteString responseBody = ((HttpEntity.Strict) result.body()).data();
        String res = responseBody.decodeString("UTF-8");

        // Converting the body of the http.Entity to an ObjectNode.
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        try {
            actualObj = mapper.readTree(res);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Getting the content body as a JasonNode.
        assert actualObj != null;
        JsonNode body = actualObj.get("body");

        // Converting JasonNode body to a ArrayList<FootballClub>.
        ArrayList<FootballClub> list = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            FootballClub footballClub = Json.fromJson(body.get(i), FootballClub.class);
            list.add(footballClub);
        }

        // Testing if the result body content is correct.
        Assertions.assertEquals(testPLM.getCLUBS(), list);
    }

    /**
     * Method to test PremierLeagueManagerController.listMatches() method.
     */
    @Test
    void listMatches() {
        // Building Get request for PremierLeagueManagerController.listMatches() .
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri(routes.PremierLeagueManagerController.listMatches(testYear).url());

        // Getting the result.
        Result result = route(application, request);

        // Testing if the result is equal to the expected result.
        Assertions.assertEquals(OK, result.status());

        // Getting the body of the http.Entity.
        ByteString responseBody = ((HttpEntity.Strict) result.body()).data();
        String res = responseBody.decodeString("UTF-8");

        // Converting the body of the http.Entity to an ObjectNode.
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        try {
            actualObj = mapper.readTree(res);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Getting the content body as a JasonNode.
        assert actualObj != null;
        JsonNode body = actualObj.get("body");

        // Converting JasonNode body to a ArrayList<Match<FootballClub>>.
        ArrayList<Match<FootballClub>> list = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            Match<FootballClub> match = Json.fromJson(body.get(i), Match.class);
            list.add(match);
        }

        // Testing if the result body content is correct.
        Assertions.assertEquals(testPLM.getMATCHES(), list);
    }

    /**
     * Method to test PremierLeagueManagerController.retrieveRandomMatch() method.
     */
    @Test
    void retrieveRandomMatch() {
        // Building Get request for PremierLeagueManagerController.retrieveRandomMatch() .
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri(routes.PremierLeagueManagerController.retrieveRandomMatch(testYear).url());

        // Getting the result.
        Result result = route(application, request);

        // Testing if the result is equal to the expected result.
        Assertions.assertEquals(OK, result.status());

        // Getting the body of the http.Entity.
        ByteString responseBody = ((HttpEntity.Strict) result.body()).data();
        String res = responseBody.decodeString("UTF-8");

        // Converting the body of the http.Entity to an ObjectNode.
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        try {
            actualObj = mapper.readTree(res);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Getting the content body as a JasonNode.
        assert actualObj != null;
        JsonNode body = actualObj.get("body");

        // Converting JasonNode body to a Match<FootballClub>.
        Match<FootballClub> match = Json.fromJson(body, Match.class);

        // Testing if the result body content is correct.
        Assertions.assertTrue(testPLM.getCLUBS().contains(match.getClubAway()) && testPLM.getCLUBS().contains(match.getClubHome()));
        Assertions.assertFalse(testPLM.getMATCHES().contains(match));
    }

    /**
     * Method to test PremierLeagueManagerController.createRandomMatch() method.
     */
    @Test
    void createRandomMatch() {
        // Creating a Random match to send in the request.
        Match<FootballClub> randomMatch = PremierLeagueManagerService.getInstance().getRandomMatch(testYear);
        // Converting the random match to Json.
        JsonNode jsonObject = Json.toJson(randomMatch);

        // Building Get request for PremierLeagueManagerController.createRandomMatch() .
        Http.RequestBuilder request = new Http.RequestBuilder()
                .bodyJson(jsonObject)
                .method(POST)
                .uri(routes.PremierLeagueManagerController.createRandomMatch(testYear).url());

        // Getting the result.
        Result result = route(application, request);

        // Testing if the result is equal to the expected result.
        Assertions.assertEquals(CREATED, result.status());

        // Getting the body of the http.Entity.
        ByteString responseBody = ((HttpEntity.Strict) result.body()).data();
        String res = responseBody.decodeString("UTF-8");

        // Converting the body of the http.Entity to an ObjectNode.
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        try {
            actualObj = mapper.readTree(res);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Getting the content body as a JasonNode.
        assert actualObj != null;
        JsonNode body = actualObj.get("body");

        // Converting JasonNode body to a Match<FootballClub>.
        Match<FootballClub> match = Json.fromJson(body, Match.class);

        // Testing if the result body content is correct.
        Assertions.assertEquals(randomMatch, match);
    }

    /**
     * Method to terminate running application after every test.
     */
    @AfterEach
    void stopApp() {
        // Terminating the Application.
        Helpers.stop(application);
    }
}