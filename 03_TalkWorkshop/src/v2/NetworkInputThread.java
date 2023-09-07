package v2;

import java.io.BufferedReader;
import java.io.IOException;

public class NetworkInputThread extends Thread {
    private BufferedReader inFromNetwork;

    public NetworkInputThread(BufferedReader inFromNetwork) {
        this.inFromNetwork = inFromNetwork;
    }

    @Override
    public void run() {
        while(true) {
            try {
                String response = inFromNetwork.readLine();
                System.out.println(response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
