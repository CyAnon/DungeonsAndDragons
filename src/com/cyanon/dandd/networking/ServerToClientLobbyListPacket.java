package com.cyanon.dandd.networking;

import java.util.ArrayList;

import com.cyanon.dandd.battle.Battle;

public class ServerToClientLobbyListPacket extends Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8332392860333915540L;
	ArrayList<Battle> thisPayload;
	
	public ServerToClientLobbyListPacket(ArrayList<Battle> input)
	{
		this.thisPayload = input;
	}

}
