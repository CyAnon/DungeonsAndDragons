package com.cyanon.dandd.networking;

import java.io.Serializable;

import com.cyanon.dandd.monsters.Monster;

//Version 1.0 of this packet

public class ClientInfoPacket extends Packet implements Serializable {
	
	private static final long serialVersionUID = -4669308677000327413L;
	
	private String clientName;
	private Monster monster;
	
	public ClientInfoPacket(String name)
	{
		setClientName(name);
	}
	
	public ClientInfoPacket(String name, Monster monster)
	{
		setClientName(name);
		setMonster(monster);
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

}
