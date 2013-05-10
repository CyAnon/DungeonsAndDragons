//THIS IS THE THREAD THAT THE SERVER STARTS WHICH CONTROLS THE CLIENT. IT IS NOT THE ACTUAL CLIENT CLASS ITSELF!!!

package com.cyanon.dandd;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

import com.cyanon.dandd.attacktype.Attack;
import com.cyanon.dandd.battle.*;
import com.cyanon.dandd.monsters.*;
import com.cyanon.dandd.networking.*;

public class DANDDClient extends Thread {
	
	protected ObjectInputStream ois;
	protected ObjectOutputStream oos;
	
	protected Battle thisBattle;
	protected Lobby thisLobby;
	
	protected String thisPlayerHandle;
	
	private Boolean clientLive = false;
	private Boolean clientInBattle = false;
	
	private int playerNumber;
	private Monster myMonster;
		
	public DANDDClient(Socket s, String thisGameName, Lobby lobby)
	{
		thisLobby = lobby;
		try
		{
			this.ois = new ObjectInputStream(s.getInputStream());
			this.oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			oos.writeObject(new ServerInfoPacket(thisLobby.getLobbyName(), 0)); //change this
			oos.flush();
			processPacket((ClientInfoPacket)ois.readObject());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		clientLive = true;
		System.out.println(thisPlayerHandle + " has joined the lobby with " + 0 + " other people..."); //Fix for person/people
		
		thisLobby.addToTestBattle(this);
		this.thisBattle = thisLobby.getNewestBattle();
	}

	public void run()
	{
		while(clientLive) //Fix this.. it's a tad shitty
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
				e.printStackTrace();
				System.exit(-1);
			}			
		}
	}

	public void refreshClientLobby() throws IOException
	{
		oos.writeObject(thisLobby);
		oos.flush();
	}
	
	public void processPacket(Packet packet) throws IOException //packet incoming
	{
		if (packet instanceof ClientInfoPacket)
		{
			this.thisPlayerHandle = packet.getClientName();
			if (packet.getMonster() != null)
			{
				this.myMonster = packet.getMonster();
				printPacketMessageToClient(new ServerToClientPacket("You have chosen " + myMonster.formattedName + " as your creature!"));
			}
		}
		else if (packet.getIsStringPacket()) //message from client arrives on the server, ready to be sent to the other client
		{
			if (!clientInBattle)
			{
				printPacketMessageToClient(packet);
			}
			else
			{
				thisBattle.sendMessageToOtherClient(this, packet); //check transfer logic
			}
		}
		else if (packet.getIsAttackPacket()) //attack arrives on the server to be applied to enemy monster, and trigger
		{									 //a message to other client to inform them they have been attacked
			if (!packet.processedByServer)
			{
				packet.processedByServer = true;
				this.thisBattle.sendPacketToOtherClient(this, packet);
			}
			else
			{
				myMonster.sufferAttack((Attack) packet.getAttack());
			}
		}
	}
	
	public void printPacketMessageToClient(Packet packet) throws IOException
	{
		//sanity check
		oos.writeObject(new ServerToClientPacket(packet.getString()));
		oos.flush();
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

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	

	public Monster getMyMonster() {
		return myMonster;
	}
	

	public void setMyMonster(Monster myMonster) {
		this.myMonster = myMonster;
	}
}
