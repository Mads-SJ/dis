package opg11;

import Serverskeleton.common;

import java.io.*;
import java.net.Socket;
public class ServerThread extends Thread {
	Socket connSocket;

	public ServerThread(Socket connSocket) {
		this.connSocket = connSocket;
	}

	public void run() {
		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connSocket.getOutputStream());

			// Do the work and the communication with the client here	
			// The following two lines are only an example

			String[] clientSentence = inFromClient.readLine().split(" ");

			if (clientSentence[1].contains(".ico")) {
				outToClient.writeBytes("HTTP 404 Not Found\n");
				outToClient.writeBytes("Connection: close\n\n");
				outToClient.close();
				return;
			}

			outToClient.writeBytes("HTTP 200 OK\n");
			outToClient.writeBytes(ContentType(clientSentence[1]) + "\n");
			outToClient.writeBytes("Connection: close\n\n");

			byte[] file = read("C:\\Users\\Mads\\Desktop\\datamatiker\\3. semester\\DIS\\DIS-code\\04_WebServer\\src\\opg11\\myWEB\\"
					+ clientSentence[1]);
			outToClient.write(file);

			outToClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// do the work here
	}

	String ContentType(String docuname) {
	//returns the Content-Type headerline for a given document-name
		if (docuname.endsWith(".html")) {
			return ("Content-Type: text/html");
		} else if (docuname.endsWith(".gif")) {
			return ("Content-Type: image/gif");
		} else if (docuname.endsWith(".png")) {
			return ("Content-Type: image/png");
		} else if (docuname.endsWith(".jpg")) {
			return ("Content-Type: image/jpg");
		} else if (docuname.endsWith(".js")) {
			return ("Content-Type: text/javascript");
		} else if (docuname.endsWith(".css")) {
			return ("Content-Type: text/css");
		} else {
			return ("Content-Type: text/plain");
		}
	}

	byte[] read(String aInputFileName) throws FileNotFoundException {
	// returns the content of a file in a binary array
		System.out.println("Reading in binary file named : " +
				aInputFileName);
		File file = new File(aInputFileName);
		System.out.println("File size: " + file.length());
		byte[] result = new byte[(int) file.length()];
		try {
			InputStream input = null;
			try {
				int totalBytesRead = 0;
				input = new BufferedInputStream(new FileInputStream(file));
				while (totalBytesRead < result.length) {
					int bytesRemaining = result.length - totalBytesRead;
					int bytesRead = input.read(result, totalBytesRead,
							bytesRemaining);
	//input.read() returns -1, 0, or more :
					if (bytesRead > 0) {

						totalBytesRead = totalBytesRead + bytesRead;
					}
				}
				System.out.println("Num bytes read: " + totalBytesRead);
			} finally {
				System.out.println("Closing input stream.");
				input.close();
			}
		} catch (FileNotFoundException ex) {
			throw ex;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return result;
	}
}