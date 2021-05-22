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

package utils;

// Imports.

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entities.FootballClub;
import org.junit.jupiter.api.Test;
import play.libs.Json;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test Class to test ApplicationUtil class.
 */
class ApplicationUtilTest {

    /**
     * Method to test ApplicationUtil.createResponse() method.
     */
    @Test
    void createResponse() {
        // Variables needed fot testing.
        ObjectNode expectedResult;
        ObjectNode actualResult;

        // Testing for a String input.
        expectedResult = Json.newObject().put("body", "Test Passed").put("status", true);
        actualResult = ApplicationUtil.createResponse("Test Passed", true);
        assertEquals(expectedResult, actualResult);

        // Testing for a JsonNode input.
        FootballClub footballClub = new FootballClub();
        JsonNode jsonNode = Json.toJson(footballClub);
        expectedResult = Json.newObject().put("status", true).set("body", jsonNode);
        actualResult = ApplicationUtil.createResponse(jsonNode, true);
        assertEquals(expectedResult, actualResult);
    }
}