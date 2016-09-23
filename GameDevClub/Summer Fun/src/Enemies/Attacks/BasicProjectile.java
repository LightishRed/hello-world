package Enemies.Attacks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Enemies.Spawn;
import Player.BasicPlayer;

public abstract class BasicProjectile 
{
	float x;
	float y;
	float TargetX;
	float TargetY;
	float directX;
	float directY;
	float speed;
	int tickCount;
	
	public BasicProjectile(Spawn attacker, BasicPlayer target, float speed)
	{
		x = attacker.getX();
		y = attacker.getY();
		
		TargetX = target.getX();
		TargetY = target.getY();
		
		directX = TargetX - x;
		directY = TargetY - y;
		
		this.speed = speed;
		
		tickCount = 0;
		
		while (Math.abs(gcd(directY, directX))!= 1)
		{
			float temp = directX;
			directX = directX / Math.abs(gcd(directY, directX));
			directY = directY / Math.abs(gcd(directY, temp));
			
		}
		
		float den = Math.max(Math.abs(directX), Math.abs(directY));
		
		directX = directX / den;
		directY = directY / den;
		
	}
	
	public void move()
	{
		move(directX ,directY);
	}
	
	public void move(float xa, float ya)
	{
		x += xa * speed;
		y += ya * speed;
	}
	
	public void tick()
	{
		tickCount ++;
		
		move();
	}
	
	private static float gcd(float a, float b)
	{
	    while (b > 0)
	    {
	        float temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	    
	    // got method from post : http://stackoverflow.com/questions/4201860/how-to-find-gcf-lcm-on-a-set-of-numbers
	}
	
	public abstract void render(GameContainer gc, Graphics g, Image sprite) throws SlickException;
}
