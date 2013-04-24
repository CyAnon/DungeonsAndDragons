package com.cyanon.dandd.battle;

import java.io.IOException;

import com.cyanon.dandd.DANDDClient;
import com.cyanon.dandd.networking.Packet;

public class Battle implements Runnable {

	public DANDDClient playerOne = null;
	public DANDDClient playerTwo = null;
	
	private Boolean battleFull = false;
	
	private int playersInBattle = 0;
	
	public Battle() {
	}

	public void joinGame(DANDDClient clientJoin)
	{
		if (playerOne == null)
		{
			this.playerOne = clientJoin;
			playersInBattle = 1;
		}
		else
		{
			this.playerTwo = clientJoin;
			playersInBattle = 2;
			battleFull = true;
			announceBattleDetails();
		}
	}
	
	private void announceBattleDetails()
	{
		System.out.println("New battle! " + playerOne.getPlayerHandle() + " VS " + playerTwo.getPlayerHandle() + "!");
		playerOne.setClientInBattle(true);
		playerTwo.setClientInBattle(true);
	}
	
	public int getPlayersInBattle()
	{
		return playersInBattle;
	}
	
	public void tick()
	{
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public void processMessagePacket(Packet packet) throws IOException {
		playerOne.printPacketMessageToClient(packet);
		playerTwo.printPacketMessageToClient(packet);
	}
	

}
