package com.cyanon.dandd.battle;

import java.util.ArrayList;

import com.cyanon.dandd.*;

public class Lobby {

	private String lobbyName;
	private int maxBattles = 50;
	
	ArrayList<Battle> thisLobby = new ArrayList<Battle>();
		
	public Lobby(String thisGameName)
	{
		this.lobbyName = thisGameName;
		System.out.println("Lobby " + lobbyName + " active and ready...");
		if (thisLobby.isEmpty() || thisLobby.get(thisLobby.size() - 1).getBattleFull() == true)
		{
			thisLobby.add(new Battle());
		}
	}
	
	public void addToTestBattle(DANDDClient player)
	{
		thisLobby.get(thisLobby.size() - 1).joinGame(player);
	}
	
	public void removeOldBattle(int index)
	{
		thisLobby.remove(index);
	}

	public String getLobbyName() {
		return lobbyName;
	}

	public void setLobbyName(String lobbyName) {
		this.lobbyName = lobbyName;
	}
	
	public Battle getNewestBattle()
	{
		return thisLobby.get(thisLobby.size() - 1);
	}

//DEPRECATED
//	public Battle getTestBattle() {
//		return testBattle;
//	}
//
//	public void setTestBattle(Battle testBattle) {
//		this.testBattle = testBattle;
//	}
	
	

}
