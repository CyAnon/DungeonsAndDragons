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
import com.cyanon.dandd.networking.ServerToClientMessagePacket;
import com.cyanon.dandd.networking.StringPacket;

public class DANDDClient extends Thread {
	
	protected ObjectInputStream ois;
	protected ObjectOutputStream oos;
	
	protected Battle thisBattle;
	protected Lobby thisLobby;
	
	protected Attack nextAttack;
	protected String thisPlayerHandle;
	
	private Boolean clientLive = false;
	private Boolean clientInBattle = false;
		
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
		System.out.println(thisPlayerHandle + " has joined the lobby with " + 0 + " other people..."); //Fix for person/people
		
		thisLobby.addToTestBattle(this);
		this.thisBattle = thisLobby.getTestBattle();
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
			} 
			catch (IOException e) 
			{
				System.err.println("Dead!");
			}
			
		}
	}

	public void refreshClientLobby() throws IOException
	{
		oos.writeObject(thisLobby);
		oos.flush();
	}
	
	private void processPacket(Packet packet) throws IOException
	{
		if (packet instanceof StringPacket)
		{
			if (!clientInBattle)
			{
				printPacketMessageToClient(packet);
			}
			else
			{
				thisBattle.processMessagePacket(packet);
			}
		}
	}
	
	public void printPacketMessageToClient(Packet packet) throws IOException
	{
		oos.writeObject(new ServerToClientMessagePacket(packet.getPayload().toString()));
	}
	
	public String getPlayerHandle()
	{
		return thisPlayerHandle;
	}

	public Boolean getClientInBattle() {
		return clientInBattle;
	}

	public void setClientInBattle(Boolean clientInBattle) {
		this.clientInBattle = clientInBattle;
	}
}
