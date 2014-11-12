package Chatclient.Objects;

import Chatclient.util.Utils;

import java.net.InetAddress;

/**
 * this class represents a recieved message.
 * basically the same like message except additional IPAddress and port fields
 * to save where the package came from
 */
public class ReceivedMessage extends Message {
    /**
     * IP address the message was received from
     */
    public final InetAddress IPAddress;
    /**
     * port the message was received on
     */
    public final int port;

    /**
     * creates a new message object. If any Value is longer than allowed, it gets truncated.
     *
     * @param username      username, must not be null
     * @param message       message text
     * @param messageNumber message number
     * @param IPAddress     the ip address the message was received from
     * @param port          port the message was received from
     */
    public ReceivedMessage(String username, String message, int messageNumber, InetAddress IPAddress, int port) {
        super(username, message, messageNumber);
        this.port = port;
        Utils.checkIfParamIsNull(IPAddress, "IPAddress");
        this.IPAddress = IPAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReceivedMessage)) return false;
        if (!super.equals(o)) return false;
        ReceivedMessage that = (ReceivedMessage) o;
        return port == that.port && IPAddress.equals(that.IPAddress);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + IPAddress.hashCode();
        result = 31 * result + port;
        return result;
    }
}
