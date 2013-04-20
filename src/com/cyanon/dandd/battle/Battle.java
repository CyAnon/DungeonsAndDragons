package com.cyanon.dandd.battle;

import com.cyanon.dandd.DANDDClient;

public class Battle {

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
	}
	
	public int getPlayersInBattle()
	{
		return playersInBattle;
	}
	
	public void tick()
	{
		
	}
	

}
