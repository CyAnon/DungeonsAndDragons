package com.cyanon.dandd.networking;

import java.io.Serializable;

import com.cyanon.dandd.attacktype.Attack;

public class AttackPacket extends Packet implements Serializable {

	private Attack attackPayload;
	
	public AttackPacket(Attack attack)
	{
		this.setAttackPayload(attack);
	}

	public Attack getAttackPayload() {
		return attackPayload;
	}

	public void setAttackPayload(Attack attackPayload) {
		this.attackPayload = attackPayload;
	}
}
