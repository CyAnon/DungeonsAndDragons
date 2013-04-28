package com.cyanon.dandd.monsters;

import com.cyanon.dandd.attacktype.*;
import com.cyanon.dandd.attacktype.element.Element;
import com.cyanon.dandd.userinteraction.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Monster implements Serializable {

	public String formattedName;
	protected int currentHealth;
	protected int currentMana;
	
	protected ArrayList<Attack> attackArray;
	protected GenericMenu attackMenu;
	
	protected Element thisMonsterElement;
	
	
	public Monster(int health_in)
	{
		this.currentHealth = health_in;
		this.currentMana = 100;
		this.attackArray = new ArrayList<Attack>();
		this.attackMenu = new GenericMenu();
		
		formatAttackMenu();
	}
	
	public int getHealth()
	{
		return currentHealth;
	}
	
	public void decreaseHealth(int decrease)
	{
		this.currentHealth -= decrease;
	}
	
	public void increaseHealth(int increase)
	{
		this.currentHealth += increase;
	}
	
	public int getAttackDamage(Attack attack, Element enemyMonsterElement)
	{
		if (attack.getAttackElement().isThisWeakAgainst(attack.getAttackElement(), enemyMonsterElement))
			return (attack.getAttackDamage() * 2);
		return attack.getAttackDamage();
	}
	
	public void attackEnemy(Attack attack, Monster enemy)
	{
		enemy.decreaseHealth(this.getAttackDamage(attack, enemy.thisMonsterElement));
	}
	
	public void tick()
	{
		if (this.currentMana < 100)
			this.currentMana += 5;
		
		for (Attack i : this.attackArray) // Mana control system
		{
			if (i.getManaRequirement() < this.currentMana)
			{
				i.availableWithCurrentMana = true;
			}
			else
			{
				i.availableWithCurrentMana = false;
			}
		}
	}
	
	public Attack getAttack(int i)
	{
		if (i < attackArray.size())
			return attackArray.get(i);
		else
			return attackArray.get(0);
	}

	public void attackEnemy(Attack attack, ObjectOutputStream oos) {
		try {
			oos.writeObject(attack);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private void formatAttackMenu()
	{
		if (this.attackArray != null || this.attackMenu != null)
		{
			for (int i = 0; i < this.attackArray.size(); i++)
			{
				this.attackMenu.setOption(this.attackArray.get(i).getAttackName(), i);
			}
		}
		else
		{
			System.err.println("Null attack structure. Dying...");
			System.exit(-1);
		}
		
	}
	
	public void sufferAttack(Attack attack)
	{
		currentHealth -= attack.getAttackDamage();
		System.out.println("Health:" + this.currentHealth);
	}
}
