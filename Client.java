package com.ss.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JTextArea;

import com.ss.chatapp.utils.ConfigReader;

public class Client {
	//Write to Network
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
	public Client(JTextArea textArea) throws UnknownHostException, IOException{
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket=new Socket(ConfigReader.getValue("SERVER_IP"),PORT);
		out=socket.getOutputStream();
		in=socket.getInputStream();
		this.textArea=textArea;
		readMessage();
//		System.out.println("Client Comes");
//		System.out.println("Enter the message to be sent to the server");
//		Scanner scanner=new Scanner(System.in);
//		String message=scanner.nextLine();
//		OutputStream out=socket.getOutputStream();//Write bytes on Network 
//		out.write(message.getBytes());
//		System.out.println("Message sent to the Server");
//		scanner.close();
//		out.close();
//		socket.close();
		
	}
	public void sendMessage(String message) throws IOException {
		message=message +"\n";  //"\n" needed here 
		out.write(message.getBytes());
	}
	public void readMessage() {
		worker=new ClientWorker(in, textArea);//Calling a read thread 
		worker.start();
	}

//	public static void main(String[] args) throws UnknownHostException, IOException {
//		// TODO Auto-generated method stub
//		Client client =new Client();

	}


