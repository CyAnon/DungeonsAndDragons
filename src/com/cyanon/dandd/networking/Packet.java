package com.cyanon.dandd.networking;

import java.io.Serializable;

import com.cyanon.dandd.monsters.Monster;

public abstract class Packet implements Serializable {

	private static final long serialVersionUID = -1738426765256602083L;
	public boolean processedByServer = false;

	public Packet()
	{
		
	}
	
	public Object getPayload()
	{
		return null;
	}
	
	public String getPayloadName()
	{
		return null;
	}

	public String getClientName() 
	{
		return null;
	}
	
	public Monster getMonster()
	{
		return null;
	}

}
