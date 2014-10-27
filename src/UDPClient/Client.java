package UDPClient;

import UDPClient.util.Console;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Client main class
 */
public class Client {
    public static void main(final String[] args) {

        InetAddress IPAddress = null;
        final int port;
        final String username;
        final Connection con;
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
        port = Integer.parseInt(Console.readLine("port: "));
        username = Console.readLine("enter username: ");

        System.out.print("trying to establish client socket...");
        con = new Connection(IPAddress, port);
        System.out.println("OK");

        //noinspection InfiniteLoopStatement
        while (true) {
            final String messageText = Console.readLine("enter message: ");
            if (messageText.toLowerCase().equals("/quit")) {
                System.out.println("quitting...");
                System.exit(0);
            }
            messageNumber++;
            final Message msg = new Message(username, messageText, messageNumber);
            con.sendMessage(msg);
        }
    }
}
