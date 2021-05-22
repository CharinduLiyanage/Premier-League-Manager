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

import java.io.*;

/**
 * Class to handle Storage.
 */
public class Storage {
    private static final String SAVE_LOCATION = settingSaveFile();

    /**
     * Method to get the save file location.
     *
     * @return Save file location.
     */
    private static String settingSaveFile() {

        String saveLocation = "";
        try {
            // Finding user myDocuments file.
            Process exec = Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
            exec.waitFor();

            InputStream inputStream = exec.getInputStream();
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();

            // Getting Path for user myDocuments file.
            String myDocuments = new String(bytes);
            myDocuments = myDocuments.split("\\s\\s+")[4];

            // Checking the save location.
            saveLocation = myDocuments + "\\Premier League Manager (Play)";

            // Opening the save location file.
            File file = new File(saveLocation);

            // Creating the directory if its unavailable.
            file.mkdir();

        }
        // Finding save location being interrupted.
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return saveLocation;
    }

    /**
     * Method to load save data from text file.
     */
    public static PremierLeagueManager load(int year) throws IOException, ClassNotFoundException {
        // Opening the file.
        FileInputStream fileInputStream = new FileInputStream(SAVE_LOCATION + "\\plm" + year + ".txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        // Loading the Premier League.
        PremierLeagueManager plm = (PremierLeagueManager) objectInputStream.readObject();

        // Closing the save file.
        objectInputStream.close();
        fileInputStream.close();
        return plm;
    }

    /**
     * Method to save Premier League.
     *
     * @param plm PremierLeagueManager object to be saved.
     */
    public static void save(PremierLeagueManager plm) throws IOException {
        // Opening the file to save.
        FileOutputStream fileOutputStream = new FileOutputStream(SAVE_LOCATION + "\\plm" + plm.getYear() + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        // Saving Premier League.
        objectOutputStream.writeObject(plm);
        objectOutputStream.flush();

        // Closing the save file.
        objectOutputStream.close();
        fileOutputStream.close();
    }
}
