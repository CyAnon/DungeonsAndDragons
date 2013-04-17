//THIS IS THE THREAD THAT THE SERVER STARTS WHICH CONTROLS THE CLIENT. IT IS NOT THE ACTUAL CLIENT CLASS ITSELF!!!

package com.cyanon.dandd;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.cyanon.dandd.attacktype.Attack;
import com.cyanon.dandd.battle.Battle;
import com.cyanon.dandd.battle.Lobby;
import com.cyanon.dandd.networking.ClientInfoPacket;
import com.cyanon.dandd.networking.Packet;
import com.cyanon.dandd.networking.ServerInfoPacket;
import com.cyanon.dandd.networking.StringPacket;

public class DANDDClient extends Thread {
	
	protected ObjectInputStream ois;
	protected ObjectOutputStream oos;
	
	protected Battle thisBattle;
	protected Lobby thisLobby;
	
	protected Attack nextAttack;
	protected String thisPlayerHandle;
	
	private Boolean clientLive = false;
		
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
			ClientInfoPacket cip = (ClientInfoPacket)ois.readObject();
			this.thisPlayerHandle = cip.getClientName();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Boo");
			e.printStackTrace();
		}
		clientLive = true;
		System.out.println(thisPlayerHandle + " has joined the lobby with " + thisLobby.addNewBattle(this) + " other people..."); //Fix for person/people
	}

	public void run()
	{
		while(clientLive)
		{
			Packet packet;
			try 
			{
				packet = (Packet)ois.readObject();
				processPacket(packet);
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (SocketException reset)
			{
				System.out.println(thisPlayerHandle + " has left the game.");
				clientLive = false;
			} catch (IOException e) {
				System.err.println("Dead!");
			}
			
		}
	}

	public void refreshClientLobby() throws IOException
	{
		oos.writeObject(thisLobby);
		oos.flush();
	}
	
	private void processPacket(Packet packet)
	{
		if (packet instanceof StringPacket)
		{
			System.out.println(thisPlayerHandle + " said:" + packet.getPayload());
		}
	}
}
