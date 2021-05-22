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

import entities.PremierLeagueManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test Class to test Storage class.
 */
class StorageTest {

    /**
     * Method to test Storage.save() method and Storage.load() method.
     */
    @Test
    void test() {
        // Initialising the variables needed for the test.
        int testYear = 200;
        PremierLeagueManager testPLM = new PremierLeagueManager(testYear);
        PremierLeagueManager loadedPLM = null;

        // Saving the PremierLeagueManager.
        try {
            Storage.save(testPLM);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Loading the saved PremierLeagueManager.
        try {
            loadedPLM = Storage.load(200);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Checking if the loaded save file is the same as the initial file.
        assertEquals(testPLM, loadedPLM);
    }
}