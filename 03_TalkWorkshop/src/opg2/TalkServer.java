package opg2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TalkServer {
    public static void main(String[] args) throws IOException {
        String[] initialRequest;
        String response;
        int port = 6969;
        ServerSocket welcomeSocket = new ServerSocket(port);
        System.out.println("Talk server kører på port: " + port);

        while(true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            initialRequest = inFromClient.readLine().split(" ");

            if (initialRequest[0].equals("snakke")) {
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Vil du snakke med " + initialRequest[1] + "? Skriv 'Ja' eller 'Nej'.");
                response = inFromUser.readLine();
                outToClient.writeBytes(response + '\n');

                if (response.equals("Ja")) {
                    System.out.println("Modparten kan nu skrive til dig.");
                    while(true) {
                        response = inFromClient.readLine();
                        System.out.println(response);
                        String message = inFromUser.readLine();
                        outToClient.writeBytes(message + '\n');
                    }
                }
            } else {
                outToClient.writeBytes("Nej");
            }
        }
    }
}
