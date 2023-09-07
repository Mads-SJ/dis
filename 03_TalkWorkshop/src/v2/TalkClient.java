package v2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class TalkClient {
    public static void main(String[] args) throws Exception, IOException {
        String[] connectionInfo;
        String initialResponse;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Venligst indtast [IP adresse der skal forbindes til] [Dit navn]: ");
        connectionInfo = inFromUser.readLine().split(" ");

        try {
            Socket clientSocket = new Socket(connectionInfo[0], 6969);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes("Snakke " + connectionInfo[1] + '\n');
            System.out.println("Venter på godkendelse fra serveren...");
            initialResponse = inFromServer.readLine();
            System.out.println(initialResponse);

            if (initialResponse.equalsIgnoreCase("ja")) {
                System.out.println("Serveren har godkendt forbindelsen. I kan nu skrive til hinanden.");
                new UserInputThread(inFromUser, outToServer).start();
                new NetworkInputThread(inFromServer).start();
            } else {
                clientSocket.close();
            }
        }
        catch (SocketException e) {
            System.out.println("Forbindelsen blev afbrudt...");
        }
    }
}
