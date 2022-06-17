package com.ss.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


//Worker==Thread
//we make thread in this class
//For job we give runnable
//once job is created via Runnable ,write job logic in run function
//Assign the job to a thread/worker

public class ServerWorker extends Thread {
	
		private Socket clientSocket;
		private InputStream in;
		private OutputStream out;
		private Server server; 
		public ServerWorker(Socket clientSocket,Server server) throws IOException {
			this.server=server;
			this.clientSocket=clientSocket;
			in=clientSocket.getInputStream();//Client data read
			out=clientSocket.getOutputStream();//Client data write
			System.out.println("New Client Comes");
			
		
	}
	@Override
	public void run() {
		//Read data from client and broadcast to all
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while(true){
				line=br.readLine();//Client read
				System.out.println("Line Read...."+line);
				if(line.equalsIgnoreCase("quit")) {
				break;	//Client chat end
			}
//			out.write(line.getBytes());//Client send
			//Broadcast to all Client
			for(ServerWorker serverWorker : server.workers) {
				line=line+"\n";  //"\n" needed here
				serverWorker.out.write(line.getBytes());
			}
		}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			try {
			if(br!=null) {
				br.close();
			}
			if(in!=null) {
				in.close();
			}
			if(out!=null) {
				out.close();
			}
			if(clientSocket!=null) {
				clientSocket.close();
			}
			}
			
			
			catch(Exception ex) {
				ex.printStackTrace();
			}
			}
			}
		
		
	}


