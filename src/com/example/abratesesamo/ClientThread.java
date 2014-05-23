package com.example.abratesesamo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

class ClientThread implements Runnable {
	
	private static final int SERVERPORT = 25678;
	private static final String SERVER_IP = "10.0.0.112";
	
	private String login, senha;
	
	public ClientThread(String login, String senha){
		this.login = login;
		this.senha = senha;
	}

	@Override
	public void run() {

		try {
			InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

			Socket socket = new Socket(serverAddr, SERVERPORT);

			PrintWriter out;
			try {
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())), true);
				out.println(login + ":" + senha);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			socket.close();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}