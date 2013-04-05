//THIS IS THE THREAD THAT THE SERVER STARTS WHICH CONTROLS THE CLIENT. IT IS NOT THE ACTUAL CLIENT CLASS ITSELF!!!

package com.cyanon.dandd;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import com.cyanon.dandd.attacktype.Attack;

public class DANDDClient extends Thread {
	
	protected ObjectInputStream ois;
	protected ObjectOutputStream oos;
	
	protected Attack nextAttack;
	protected String thisPlayerHandle;
		
	public DANDDClient(Socket s, String thisGameName) throws IOException
	{
		try
		{
			this.ois = new ObjectInputStream(s.getInputStream());
			this.oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			oos.writeObject(thisGameName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void run()
	{
		
	}

}
