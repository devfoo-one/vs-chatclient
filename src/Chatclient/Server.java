package Chatclient;

import Chatclient.Objects.Message;
import Chatclient.Sockets.ServerSocket;

/**
 * Server class
 */
public class Server {

    static int PORT = 39999;

    public static void main(final String[] args) {
        System.out.print("trying to bind server to port " + PORT + "... ");
        final ServerSocket socket = new ServerSocket(PORT);
        System.out.println("OK. listening...");
        //noinspection InfiniteLoopStatement
        while (true) {
            final Message m = socket.receiveMessage();
            System.out.println(m.username + "(#" + m.messageNumber + "): " + m.message);
        }
    }
}
