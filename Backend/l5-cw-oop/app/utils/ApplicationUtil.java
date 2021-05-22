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
import play.libs.Json;

/**
 * Class to make the output format.
 */
public class ApplicationUtil {

    /**
     * Method to make the out put format.
     *
     * @param response The body of the output.
     * @param ok       Status of the output.
     * @return The formatted output.
     */
    public static ObjectNode createResponse(Object response, boolean ok) {
        // Initialising result.
        ObjectNode result = Json.newObject();

        // Adding the body of the result.
        if (response instanceof String) {
            result.put("body", (String) response);
        } else {
            result.set("body", (JsonNode) response);
        }

        // Adding the status of the result.
        result.put("status", ok);

        // Returning the generated result.
        return result;
    }
}
