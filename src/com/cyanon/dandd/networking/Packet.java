package com.cyanon.dandd.networking;

import java.io.Serializable;

public abstract class Packet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1738426765256602083L;

	public Packet()
	{
		
	}
	
	public Object getPayload()
	{
		return null;
	}

	public String getClientName() 
	{
		return null;
	}

}
