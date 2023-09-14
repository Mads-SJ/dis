package broadcast;

import jdk.management.jfr.RecordingInfo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
 
class UDPServer 
{ 
   public static void main(String args[]) throws Exception 
      { 
         DatagramSocket serverSocket = new DatagramSocket(7777);
         byte[] receiveData = new byte[1024];
            while(true) 
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
                  serverSocket.receive(receivePacket); 
                  String sentence = new String( receivePacket.getData(), 0, receivePacket.getLength());
                  System.out.println("RECEIVED: " + sentence);
               } 
      } 
}
