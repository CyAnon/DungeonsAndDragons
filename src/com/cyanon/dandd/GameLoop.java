package com.cyanon.dandd;

import java.io.IOException;
import java.io.InputStream;
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
	
	ArrayList<ObjectOutputStream> connectedClientStreams = new ArrayList<ObjectOutputStream>();
	
	protected boolean gamePlaying = false;
	
	private int playersOnline = 0;
	
	public GameLoop() {
		System.out.println("Starting Dungeons and Dragons...");
	}
	
	protected Monster serverMonster;
	protected Monster playerMonster;
	
	public void start()
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
				connectedClientStreams.add(new ObjectOutputStream(client.getOutputStream()));
				Thread t = new Thread(new DANDDClient(client));
				t.start();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		
	}
	
	protected void printInterface() //DUMPED CODE HERE: NOT ACTUAL METHOD! DO NOT CALL!
	{
		serverMonster = new Hydra(100);
		System.out.println("New battle ready: " + playerMonster.formattedName + " VS " + serverMonster.formattedName + "!");
			
		gamePlaying = true;
		
		while(gamePlaying)
		{
			playerMonster.tick();
			serverMonster.tick(); //tick the server monster when the player monster makes its move, and vice versa
		}
	}

}
