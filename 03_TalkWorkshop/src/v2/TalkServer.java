package v2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TalkServer {
    public static void main(String[] args) throws IOException {
        String[] initialRequest;
        String response;
        int port = 6969;
        ServerSocket welcomeSocket = new ServerSocket(port);
        System.out.println("Talk server kører på port: " + port);

        while(true) {
            try {
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println("Klient forbundet fra (IP: " + connectionSocket.getInetAddress() + ", port: " + connectionSocket.getLocalPort() + ")");
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                initialRequest = inFromClient.readLine().split(" ");
                String name = initialRequest[1];

                if (!initialRequest[0].equalsIgnoreCase("snakke")) {
                    outToClient.writeBytes("Nej");
                }
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Vil du godkende forbindelsen fra " + name + "? Skriv 'Ja' eller 'Nej'.");
                response = inFromUser.readLine();
                outToClient.writeBytes(response + '\n');

                if (response.equalsIgnoreCase("ja")) {
                    System.out.println("I kan nu skrive til hinanden.");
                    new NetworkInputThread(inFromClient).start();
                    new UserInputThread(inFromUser, outToClient).start();
                }
            }
            catch (SocketException e) {
                System.out.println("Klienten afbrød forbindelsen...");
            }
        }
    }
}
