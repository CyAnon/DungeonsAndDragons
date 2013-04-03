//THIS IS THE THREAD THAT THE SERVER STARTS WHICH CONTROLS THE CLIENT. IT IS NOT THE ACTUAL CLIENT CLASS ITSELF!!!

package com.cyanon.dandd;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import com.cyanon.dandd.attacktype.Attack;

public class DANDDClient extends Thread {
	
	protected ObjectInputStream ois;
	
	protected Attack nextAttack;
		
	public DANDDClient(Socket s)
	{
		try
		{
			this.ois = new ObjectInputStream(s.getInputStream());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		//System.out.println("A player has joined! Currently " + ++playersOnline + " online right now...");
	}

	public void run()
	{
		this.nextAttack = null;
		
		try
		{
			nextAttack = (Attack)ois.readObject();
		}
		catch (ClassNotFoundException | IOException e1)
		{
			System.err.println("Fucking annoying networking error. God fucking dammit.");
			e1.printStackTrace();
		}
		System.out.println("Player chose " + this.nextAttack.getAttackName() + " and dealt " + nextAttack.getAttackDamage() + " damage!");
	}

}
