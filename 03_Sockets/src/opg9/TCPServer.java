package opg9;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) throws Exception {
		
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomSocket = new ServerSocket(6789);
		System.out.println("Server is running");
		
		while(true){
			Socket connectionSocket = welcomSocket.accept();
			connectionSocket.setSoTimeout(5000);
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			clientSentence = inFromClient.readLine();
			System.out.println("From client (IP: " + connectionSocket.getLocalAddress() + ", PORT: " + connectionSocket.getLocalPort() + "): " + clientSentence);
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			connectionSocket.close();
			outToClient.writeBytes(capitalizedSentence);
		}

	}

}
