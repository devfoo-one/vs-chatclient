package UDPClient;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * this class represents one connection to a given server
 */
public class Connection {
    private InetAddress IPAddress;
    private int port;
    private DatagramSocket socket;
    private int messageNumber;

    /**
     * creates a new connection object
     *
     * @param ip   IP-Address
     * @param port port
     */
    public Connection(final InetAddress ip, final int port) {
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            System.err.println("could not establish client socket!");
            e.printStackTrace();
        }
        this.IPAddress = ip;
        this.port = port;
    }

    public DatagramPacket getPackage(final Message mObj) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final DataOutputStream dout = new DataOutputStream(out);

        // make shure that username is always 8 byte long
        byte[] usernameFixed = new byte[8];
        for (int i = 0; i < mObj.username.length(); i++) {
            usernameFixed[i] = (byte) mObj.username.charAt(i);
        }

        try {
            dout.writeInt(this.messageNumber);
            dout.write(usernameFixed);
            dout.writeBytes(mObj.message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final byte[] byteArray = out.toByteArray();
        return new DatagramPacket(byteArray, byteArray.length, this.IPAddress, this.port);
    }

    public void sendMessage(final Message m) {
        try {
            this.socket.send(getPackage(m));
        } catch (IOException e) {
            System.err.println("could not send message!");
            e.printStackTrace();
        }
        this.messageNumber++;
    }
}
