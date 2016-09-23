package Enemies;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Level.Level;
import Player.BasicPlayer;

public class Healer extends Spawn
{
	Image sprite;
	Level level;
	long lastTick;

	public Healer(String name, Level level, BasicPlayer player, float x, float y, float Speed) 
	{
		super(name, level, player, x, y, Speed);
		
		lastTick = System.currentTimeMillis();
		movable = true;
		
		maxHealth = 10;
		health = maxHealth;
		
		this.level = level;
		
		try {
			sprite = new Image("res/Enemies/Healer/Healer.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		g.drawImage(sprite, x, y);
		
	}

	@Override
	public void tick(long delta) 
	{
		super.tick();
		
		ArrayList<Spawn> derp = level.getMobs();
		
		for(Spawn patient: derp)
		{
			
			if(patient == this)
			{
				
			}else
			{
				if(patient.damaged)
				{
					if(!distanceSense(50, patient))
					{
						follow(patient);
					}else
					{
						if(System.currentTimeMillis() - lastTick >= 500)
						{
							patient.regen(1);System.out.println("derp");
							lastTick = System.currentTimeMillis();
						}
					}
				}
			}
		}
	}
	
	public void follow(Spawn patient)
	{
		float distX = patient.getX() - x;
		float distY = patient.getY() - y;
		
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
	
	public boolean distanceSense(float distance, Spawn patient)
	{
		double space = Math.sqrt(Math.pow(x - 
				patient.getX(), 2) + Math.pow(y - patient.getY(), 2));
		
		if(space <= distance)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}

}
