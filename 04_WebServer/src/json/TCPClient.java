package json;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import org.json.*;

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

		DataTransferObject dto = new DataTransferObject("Personer", personer);

		Socket clientSocket = new Socket("localhost",6789);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		JSONObject jsonDTO = new JSONObject(dto);
		outToServer.writeBytes(jsonDTO.toString() + '\n');
		clientSocket.close();
		System.out.println("Personer sendt til serveren");
	}
}


