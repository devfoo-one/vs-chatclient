package UDPClient;

/**
 * message for the chat-client
 * structure:
 * 4 bytes message number
 * 8 bytes username
 */
public class Message {
    public final String username;
    public final String message;

    /**
     * creates a new message object. If any Value is longer than allowed, it gets truncated.
     *
     * @param username username, must not be null
     * @param message  message text
     */
    public Message(final String username, String message) {
        if (username == null) {
            throw new IllegalArgumentException("username must not be null");
        }
        if (message == null) {
            message = "";
        }

        // if username is longer than 8 chars, truncate...
        if (username.length() > 8) {
            this.username = username.substring(0, 7);
        } else {
            this.username = username;
        }

        if (message.length() > 138) {
            this.message = message.substring(0, 137);

        } else {
            this.message = message;
        }

    }


}
