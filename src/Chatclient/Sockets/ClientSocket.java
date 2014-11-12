package Chatclient.Sockets;

import Chatclient.Objects.Message;

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
public class ClientSocket {
    private InetAddress IPAddress;
    private int port;
    private DatagramSocket socket;

    /**
     * creates a new connection object
     *
     * @param ip   IP-Address
     * @param port port
     */
    public ClientSocket(final InetAddress ip, final int port) {
        this.IPAddress = ip;

        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            System.err.println("could not establish client socket!");
            e.printStackTrace();
        }
        if (port < 1 || port > 65535) {
            throw new IllegalArgumentException("port must be between 0 - 65535");
        } else {
            this.port = port;
        }
    }

    public DatagramPacket getPackage(final Message mObj) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final DataOutputStream dout = new DataOutputStream(out);

        // make shure that username is always 8 byte long
        byte[] usernameFixed = new byte[8];
        for (int i = 0; i < mObj.username.length(); i++) {
            usernameFixed[i] = (byte) mObj.username.charAt(i);
        }

        // make shure that message is always 138 byte long
        byte[] messageFixed = new byte[138];
        for (int i = 0; i < mObj.message.length(); i++) {
            messageFixed[i] = (byte) mObj.message.charAt(i);
        }

        try {
            dout.writeInt(mObj.messageNumber);
            dout.write(usernameFixed);
            dout.write(messageFixed);
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
    }

    public void close() {
        this.socket.close();
    }
}
