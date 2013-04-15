package com.cyanon.dandd.battle;

import com.cyanon.dandd.DANDDClient;

public class Battle {

	public DANDDClient playerOne;
	public DANDDClient playerTwo;
	
	public Battle(DANDDClient clientJoin) {
		this.playerOne = clientJoin;
	}

	public void joinGame(DANDDClient clientJoin)
	{
		if (playerOne != null)
			this.playerTwo = clientJoin;
	}
	
	public void tick()
	{
		
	}
	

}
