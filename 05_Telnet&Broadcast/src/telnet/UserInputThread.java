package telnet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class UserInputThread extends Thread {
    private BufferedReader inFromUser;
    private DataOutputStream out;

    public UserInputThread(BufferedReader inFromUser, DataOutputStream out) {
        this.inFromUser = inFromUser;
        this.out = out;
    }

    @Override
    public void run() {
        while(true) {
            try {
                String message = inFromUser.readLine();
                out.writeBytes(message + '\n');
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
