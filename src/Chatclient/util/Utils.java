package Chatclient.util;

/**
 * general static helpers
 */
public class Utils {
    public static void checkIfParamIsNull(final Object o, final String paramName) {
        if (o == null) {
            throw new IllegalArgumentException(paramName + " must not be null!");
        }
    }

}
