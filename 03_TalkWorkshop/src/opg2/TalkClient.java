package opg2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TalkClient {
    public static void main(String[] args) throws Exception, IOException {
        String[] connectionInfo;
        String initialResponse;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Venligst indtast [IP adresse] [Dit navn]: ");
        connectionInfo = inFromUser.readLine().split(" ");

        Socket clientSocket = new Socket(connectionInfo[0], 6969);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes("Snakke " + connectionInfo[1] + '\n');
        initialResponse = inFromServer.readLine();

        if (initialResponse.equals("Ja")) {
            System.out.println("Du kan nu skrive til modparten.");
            while(true) {
                String message = inFromUser.readLine();
                outToServer.writeBytes(message + '\n');
                String response = inFromServer.readLine();
                System.out.println(response);
            }
        } else {
            clientSocket.close();
        }

    }
}
