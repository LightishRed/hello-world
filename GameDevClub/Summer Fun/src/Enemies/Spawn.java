package Enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Level.Level;
import Player.BasicPlayer;

public abstract class Spawn
{
	String name;
	BasicPlayer protag;
	float x;
	float y;
	float speed;
	boolean movable;
	boolean damaged;
	int tickCount;
	int damage;
	int health;
	int maxHealth;
	Level level;
	
	public Spawn(String name, Level level, BasicPlayer player, float x, float y, float Speed)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		speed = Speed;
		tickCount = 0;
		level = new Level(player);
		damaged = false;
		
		protag = player;
	}
	
	public boolean distanceSense(float distance)
	{
		double space = Math.sqrt(Math.pow(x - 
				protag.getX(), 2) + Math.pow(y - protag.getY(), 2));
		
		if(space <= distance)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public void follow()
	{
		float distX = protag.getX() - x;
		float distY = protag.getY() - y;
		
		if(distY != 0)
		{
			try
			{
				move(0, (distY) / (Math.abs(distY))); // 
			}
			catch(Exception e)
			{
				move(0, 0);
			}
		}else
		{
			move(0,0);
		}
		
		if(distX != 0)
		{
			try
			{
				move((distX) / (Math.abs(distX)) ,0); //
			}
			catch(Exception e)
			{
				move(0, 0);
			}
		}else
		{
			move(0,0);
		}
	}
	
	public void move(float xa, float ya)
	{
		if(movable)
		{
			x += xa * speed;
			y += ya * speed;
		}
	}
	
	public void die()
	{
		level.removeSpawn(this);
	}
	
	public void damage(int dmg)
	{
		health -= dmg;
	}
	
	public void regen(int regen)
	{
		health += regen;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public abstract void render(GameContainer gc, Graphics g) throws SlickException;
	public abstract void tick(long delta);
	
	public void tick()
	{
		tickCount ++;
		
		if(health <= 0)
		{
			die();
		}else if(health < maxHealth)
		{
			damaged = true;
		}else
		{
			damaged = false;
		}
	}

	public String getName() {
		return name;
	}
	
}
