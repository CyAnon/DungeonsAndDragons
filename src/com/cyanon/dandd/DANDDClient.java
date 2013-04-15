//THIS IS THE THREAD THAT THE SERVER STARTS WHICH CONTROLS THE CLIENT. IT IS NOT THE ACTUAL CLIENT CLASS ITSELF!!!

package com.cyanon.dandd;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import com.cyanon.dandd.attacktype.Attack;
import com.cyanon.dandd.battle.Battle;
import com.cyanon.dandd.battle.Lobby;
import com.cyanon.dandd.networking.ServerInfoPacket;

public class DANDDClient extends Thread {
	
	protected ObjectInputStream ois;
	protected ObjectOutputStream oos;
	
	protected Battle thisBattle;
	protected Lobby thisLobby;
	
	protected Attack nextAttack;
	protected String thisPlayerHandle;
		
	public DANDDClient(Socket s, String thisGameName) throws IOException
	{
		try
		{
			this.ois = new ObjectInputStream(s.getInputStream());
			this.oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			oos.writeObject(thisGameName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		this.thisBattle = new Battle(this);
	}
	
	public DANDDClient(Socket s, String thisGameName, Battle battleToJoin) throws IOException
	{
		try
		{
			this.ois = new ObjectInputStream(s.getInputStream());
			this.oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			oos.writeObject(thisGameName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		battleToJoin.joinGame(this);
	}

	public DANDDClient(Socket s, String thisGameName, Lobby lobby) 
	{
		thisLobby = lobby;
		try
		{
			this.ois = new ObjectInputStream(s.getInputStream());
			this.oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			oos.writeObject(new ServerInfoPacket(thisLobby.getLobbyName(), 0));
			oos.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Joining lobby with " + thisLobby.addNewBattle(this) + " people...");
	}

	public void run()
	{

	}

	public void refreshClientLobby() throws IOException
	{
		oos.writeObject(thisLobby);
		oos.flush();
	}
}
