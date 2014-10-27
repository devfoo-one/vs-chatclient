package Chatclient;

import Chatclient.Objects.Message;
import Chatclient.Sockets.ClientSocket;
import Chatclient.util.Console;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Client main class
 */
public class Client {
    public static void main(final String[] args) {

        InetAddress IPAddress = null;
        Integer port = null;
        final String username;
        final ClientSocket con;
        int messageNumber = 0;

        System.out.println("client starting...");

        while (IPAddress == null) {
            final String ip = Console.readLine("enter server address: ");
            try {
                IPAddress = InetAddress.getByName(ip);
            } catch (UnknownHostException e) {
                System.out.println("could not find host!");
            }
        }

        while (port == null) {
            try {
                port = Integer.parseInt(Console.readLine("port: "));
            } catch (NumberFormatException e) {
                System.out.println("illegal port format!");
            }
            if (port != null && (port < 1 || port > 65535)) {
                System.out.println("port out of range!");
                port = null;
            }
        }

        username = Console.readLine("enter username: ");
        System.out.print("trying to establish client socket...");
        con = new ClientSocket(IPAddress, port);
        System.out.println(" OK (enter \"/quit\" to quit)");

        //noinspection InfiniteLoopStatement
        while (true) {
            final String messageText = Console.readLine("enter message: ");
            if (messageText.toLowerCase().equals("/quit")) {
                System.out.println("quitting...");
                con.close();
                System.exit(0);
            }
            messageNumber++;
            final Message msg = new Message(username, messageText, messageNumber);
            con.sendMessage(msg);
        }
    }
}
