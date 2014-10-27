package Chatclient.Objects;

import Chatclient.util.Utils;

/**
 * message for the chat-client
 * structure:
 * 4 bytes message number
 * 8 bytes username
 * xxx bytes message
 */
public class Message {
    public final String username;
    public final String message;
    public final int messageNumber;

    /**
     * creates a new message object. If any Value is longer than allowed, it gets truncated.
     *
     * @param username username, must not be null
     * @param message  message text
     */
    public Message(final String username, String message, final int messageNumber) {
        this.messageNumber = messageNumber;

        Utils.checkIfParamIsNull(username, "username");
        if (message == null) {
            message = "";
        }

        // if username is longer than 8 chars, truncate...
        if (username.length() > 8) {
            this.username = username.substring(0, 8);
        } else {
            this.username = username;
        }

        if (message.length() > 138) {
            this.message = message.substring(0, 138);
        } else {
            this.message = message;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message1 = (Message) o;
        return message.equals(message1.message) && username.equals(message1.username);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }
}
