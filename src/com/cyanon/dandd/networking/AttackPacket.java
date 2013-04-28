package com.cyanon.dandd.networking;

import java.io.Serializable;

import com.cyanon.dandd.attacktype.Attack;

public class AttackPacket extends Packet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8027209390023469280L;
	private Attack attackPayload;
	public boolean processedByServer = false;
	
	public AttackPacket(Attack attack)
	{
		this.setAttackPayload(attack);
	}

	public Attack getPayload() {
		return attackPayload;
	}

	public void setAttackPayload(Attack attackPayload) {
		this.attackPayload = attackPayload;
	}
}
