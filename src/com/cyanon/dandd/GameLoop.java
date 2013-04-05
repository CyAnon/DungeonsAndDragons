package com.cyanon.dandd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.cyanon.dandd.monsters.*;

public class GameLoop {

	static ServerSocket ss;
	static Socket s;
	static 	ObjectInputStream ois;
	static InputStream is;
	
	private InputStreamReader isr;
	private BufferedReader br;
	
	//ArrayList<ObjectOutputStream> connectedClientStreams = new ArrayList<ObjectOutputStream>();
	ArrayList<Thread> connectedClientThreads = new ArrayList<Thread>();
	
	public boolean gamePlaying = false;
	
	//private int playersOnline = 0;
	
	private String thisGameName;
	
	public GameLoop() throws IOException {
		this.isr = new InputStreamReader(System.in);
		this.br = new BufferedReader(isr);
		
		System.out.println("Starting Dungeons and Dragons...");
		System.out.println("Please enter a game name below :-");
		this.thisGameName = br.readLine();
		System.out.println("Thank you. Initiating game " + this.thisGameName + "...");
	}
	
	public void start()
	{	
		while (gamePlaying)
		{
			try {
				ss = new ServerSocket(54949);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("Waiting on a player to join on port 54949...");
	
			try
			{
				Socket client = ss.accept();
				//connectedClientStreams.add(new ObjectOutputStream(client.getOutputStream()));
				Thread t = new Thread(new DANDDClient(client, thisGameName));
				connectedClientThreads.add(t);
				t.start();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		stop();
	}
	
	public void stop()
	{
		for (Thread t : connectedClientThreads)
		{
			t.interrupt();
		}
		System.out.println("Killing game server...");
	}
}
