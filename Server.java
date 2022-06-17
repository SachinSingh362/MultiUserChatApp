package com.ss.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.ss.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker>workers=new ArrayList<>();//Contains all the client sockets
	public Server() throws IOException {
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket=new ServerSocket(PORT);
		System.out.println("Server starts and waiting for Clients to join...");
		handleClientRequest();
		
		
	}
	//Multiple client handShaking
	public void handleClientRequest() throws IOException {   
		while(true) {
			Socket clientSocket=serverSocket.accept();//HandShaking
			ServerWorker serverWorker=new ServerWorker(clientSocket,this);	//Creating a new Thread
			workers.add(serverWorker);
			serverWorker.start();
			}
		
	}
	
//	For Single Client HandShaking
//	public Server() throws IOException {
//		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
//		serverSocket=new ServerSocket(PORT);
//				System.out.println("Server Started and waiting for theClient Connection...");
//				Socket socket=serverSocket.accept();//Hand Shaking of server with client
//				System.out.println("Client joins the server...");
//				InputStream in=socket.getInputStream();//read bytes from network
//				byte arr[]=in.readAllBytes();
//				String str=new String(arr);//bytes converted into Strings
//				System.out.println("Message recived from Client"+str);
//				in.close();
//				socket.close();
//	}
//	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server=new Server();

	}

}
 