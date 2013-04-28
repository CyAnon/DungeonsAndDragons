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
			this.playerOne.setPlayerNumber(1);
			playersInBattle = 1;
		}
		else
		{
			this.playerTwo = clientJoin;
			this.playerOne.setPlayerNumber(2);
			playersInBattle = 2;
			setBattleFull(true);
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
	
	public void sendPacketToOtherClient(DANDDClient clientFrom, Packet packet) throws IOException {
		if (playerOne.getPlayerNumber() == clientFrom.getPlayerNumber())
			playerTwo.processPacket(packet);
		else
			playerOne.processPacket(packet);
	}
	
	public void sendMessageToOtherClient(DANDDClient clientFrom, Packet packet) throws IOException {
		if (playerOne.getPlayerNumber() == clientFrom.getPlayerNumber())
			playerTwo.printPacketMessageToClient(packet);
		else
			playerOne.printPacketMessageToClient(packet);
	}
		
	public DANDDClient getOtherClient(DANDDClient in)
	{
		if (playerOne.getPlayerNumber() == in.getPlayerNumber())
			return playerTwo;
		else
			return playerOne;
	}

	public Boolean getBattleFull() {
		return battleFull;
	}

	public void setBattleFull(Boolean battleFull) {
		this.battleFull = battleFull;
	}
	

}
