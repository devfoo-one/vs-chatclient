package Chatclient.util;

/**
 * general static helpers
 */
public class Utils {
    /**
     * checks if o is null and throws IllegalArgumentException if so
     *
     * @param o         Object to be checked
     * @param paramName name of Object for error Message
     */
    public static void checkIfParamIsNull(final Object o, final String paramName) {
        if (o == null) {
            throw new IllegalArgumentException(paramName + " must not be null!");
        }
    }

}
