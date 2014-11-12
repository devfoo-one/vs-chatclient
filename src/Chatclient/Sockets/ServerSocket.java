package Chatclient.Sockets;

import Chatclient.Objects.ReceivedMessage;

import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

/**
 * this class represents the server socket
 */
public class ServerSocket {

    DatagramSocket serverSocket = null;

    public ServerSocket(final int port) {
        try {
            this.serverSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.err.println("could not bind socket to port " + port);
            e.printStackTrace();
        }
    }

    public ReceivedMessage receiveMessage() {
        final byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            serverSocket.receive(receivePacket);
        } catch (IOException e) {
            System.err.println("error while trying to receive message packet!");
            e.printStackTrace();
        }

        // http://stackoverflow.com/questions/5399798/byte-array-and-int-conversion-in-java
        final int messageNumber = new BigInteger(Arrays.copyOfRange(receiveData, 0, 4)).intValue();
        final String username = new String(Arrays.copyOfRange(receiveData, 4, 12)).trim();
        final String message = new String(Arrays.copyOfRange(receiveData, 12, receiveData.length - 1)).trim();
        final int port = receivePacket.getPort();
        final InetAddress IPAddress = receivePacket.getAddress();
        return new ReceivedMessage(username, message, messageNumber, IPAddress, port);
    }

}
