package com.cyanon.dandd.networking;

import java.io.Serializable;

public class StringPacket extends Packet implements Serializable {

	private String myMessage;
	
	public StringPacket(String input)
	{
		setMyMessage(input);
	}

	public String getMyMessage() {
		return myMessage;
	}

	public void setMyMessage(String myMessage) {
		this.myMessage = myMessage;
	}
	
	public String getPayload()
	{
		return myMessage;
	}

}
