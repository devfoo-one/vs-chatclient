package UDPClient.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * static console helpers
 */
public class Console {

    /**
     * show an input prompt and read a line from the console
     *
     * @param prompt prompt for the input
     * @return entered line
     */
    public static String readLine(final String prompt) {
        System.out.print(prompt);
        return readLine();
    }

    /**
     * read a line from the console
     *
     * @return entered line
     */
    public static String readLine() {
        BufferedReader inFromConsole = new BufferedReader(new InputStreamReader(System.in));
        try {
            return inFromConsole.readLine();
        } catch (IOException e) {
            System.err.println("could not read line from console...");
            e.printStackTrace();
            return "";
        }
    }
}
