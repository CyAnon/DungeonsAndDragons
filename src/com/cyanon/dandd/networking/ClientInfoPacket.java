package com.cyanon.dandd.networking;

import java.io.Serializable;

//Version 1.0 of this packet

public class ClientInfoPacket extends Packet implements Serializable {
	
	private static final long serialVersionUID = -4669308677000327413L;
	private String clientName;
	
	public ClientInfoPacket(String name)
	{
		setClientName(name);
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

}
