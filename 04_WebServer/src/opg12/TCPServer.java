package opg12;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class TCPServer {
	
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			ObjectInputStream objectInputStream = new ObjectInputStream(connectionSocket.getInputStream());

			@SuppressWarnings("unchecked")
			List<Person> personer = (List<Person>) objectInputStream.readObject();
			System.out.println("Personer modtaget fra klienten.");

			for (Person person : personer) {
				System.out.println(person);
			}
		}
	}

}
