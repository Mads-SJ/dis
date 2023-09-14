package opg12;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class TCPClient {

	public static void main(String argv[]) throws Exception{
		Person p1 = new Person("Hans", "Horsens");
		Person p2 = new Person("Grethe", "Greve");
		Person p3 = new Person("Jens", "Jelling");
		Person p4 = new Person("Bente", "Ballerup");
		ArrayList<Person> personer = new ArrayList<Person>();
		personer.add(p1);
		personer.add(p2);
		personer.add(p3);
		personer.add(p4);

		Socket clientSocket = new Socket("localhost",6789);
		ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
		outToServer.writeObject(personer);
		clientSocket.close();
		System.out.println("Personer sendt til serveren");
	}
}


