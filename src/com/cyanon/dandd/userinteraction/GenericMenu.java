package com.cyanon.dandd.userinteraction;

import java.util.ArrayList;

public class GenericMenu {

	protected ArrayList<String> menuOptions;
	
	public GenericMenu() {
		this.menuOptions = new ArrayList<String>();
	}
	
	public void setOption(String string, int index)
	{
		this.menuOptions.add(index, string);
	}
	
	public String returnOptionString(int index)
	{
		return menuOptions.get(index);
	}
	
	public int returnOptionNumber(String string)
	{
		return menuOptions.indexOf(string);
	}
	
	public void replaceOption(String string, int index)
	{
		this.menuOptions.set(index, string);
	}
	
	public ArrayList<String> getArray()
	{
		return this.menuOptions;
	}
	
	public void printMenuToScreen()
	{
		for (int i = 1; i < (menuOptions.size() - 1); i++)
		{
			System.out.println(i + ": " + menuOptions.get(i).toString());
			System.out.print(">> ");
		}
	}
}
