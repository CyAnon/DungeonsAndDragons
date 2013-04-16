package com.cyanon.dandd.networking;

import java.io.Serializable;

//Version 1.0 of this packet

public class ServerInfoPacket extends Packet implements Serializable {

	private static final long serialVersionUID = -6219615972725616509L;
	
	private String serverName;
	private int serverPlayers;
	
	public ServerInfoPacket(String name)
	{
		setServerName(name);
	}
	
	public ServerInfoPacket(int players)
	{
		setServerPlayers(players);
	}
	
	public ServerInfoPacket(String name, int players)
	{
		setServerName(name);
		setServerPlayers(players);
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public int getServerPlayers() {
		return serverPlayers;
	}

	public void setServerPlayers(int serverPlayers) {
		this.serverPlayers = serverPlayers;
	}
}
