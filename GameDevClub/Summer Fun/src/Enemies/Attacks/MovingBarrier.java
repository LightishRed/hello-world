package Enemies.Attacks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Enemies.Spawn;
import Player.BasicPlayer;

public class MovingBarrier extends Shield {
	
	private float sp;
	private float x;
	private float y;
	Image defSprite;
	
	public MovingBarrier( int range, BasicPlayer player, Spawn guard, float sp)
	{
		super( range, player, guard);
		this.sp=sp;
		x= guard.getX();
		y= guard.getY();
		
		try {
			defSprite = new Image("res/Enemies/Guardian/Shield.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void tick(int delta)
	{
		super.tick();
		x= guard.getX();
		y= guard.getY();
		//move();
	}
	
	public void move()
	{
		float directX=player.getX()-x;
		float directY=player.getY()-y;
		
		while (Math.abs(gcd(directY, directX))!= 1)
		{
			float temp = directX;
			directX = directX / Math.abs(gcd(directY, directX));
			directY = directY / Math.abs(gcd(directY, temp));
			
		}
		
		float den = Math.max(Math.abs(directX), Math.abs(directY));
		
		directX = directX / den;
		directY = directY / den;
		
		move(directX, directY);
		
	}
	
	public void move(float xa, float ya)
	{
		if (Math.abs((Math.max(x, guard.getX())))-Math.abs(Math.min(x, guard.getX()))<=range)
			x += xa*sp;
		else if (guard.getX()> x)
			x++;
		else x--;
		
		if (Math.abs((Math.max(y, guard.getY())))-Math.abs(Math.min(y, guard.getY()))<=range)
			y += ya*sp;
		else if (guard.getY()> y)
			y++;
		else y--;
		
	}
	
	public void render(GameContainer gc, Graphics g, Image sprite) throws SlickException
	{
		this.sprite= sprite;
		sprite.setCenterOfRotation(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setRotation(20);
		sprite.draw(x, y);
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
}
