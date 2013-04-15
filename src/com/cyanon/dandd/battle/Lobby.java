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
	}
	
	public int addNewBattle(DANDDClient firstPlayer)
	{
		thisLobby.add(new Battle(firstPlayer));
		System.out.println("Welcome to the lobby!");
		return thisLobby.size();
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
	
	

}
