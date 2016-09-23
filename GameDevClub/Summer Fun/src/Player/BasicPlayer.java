package Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Player.Attacks.ShortMelee;

public class BasicPlayer
{
	Image startSprite;
	int faceDir = 0;
	Input input = new Input(1);
	String name;
	float x;
	float y;
	int health;
	float tickCount;
	float speed;
	long lastTick;
	boolean sprinting;
	boolean autoattacking;
	
	ShortMelee atk;
	boolean attacking;
	
	public BasicPlayer(String name, float startX, float startY)
	{
		this.name = name;
		
		x = startX;
		y = startY;
		tickCount = 0;
		speed = 1;
		lastTick = System.currentTimeMillis();
		sprinting = false;
		attacking = false;
		autoattacking = false;
		
		health = 25;
		
		try 
		{
			startSprite = new Image("res/Players/Basic/Small Basic Ninja.png");
		} catch (SlickException e) 
		{
			System.out.println("Image not found");
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawImage(startSprite, x, y);
		
		if(attacking)
		{
			atk.render(gc, g);
		}
	}
	
	public void tick(long delta)
	{
		tickCount ++;
		
		if(input.isKeyDown(input.KEY_W))
		{
			move(0, -1);
			faceDir = 1;
		}
		if(input.isKeyDown(input.KEY_S))
		{
			move(0, 1);
			faceDir = 3;
		}
		if(input.isKeyDown(input.KEY_D))
		{
			move(1, 0);
			faceDir = 2;
		}
		if(input.isKeyDown(input.KEY_A))
		{
			move(-1, 0);
			faceDir = 4;
		}
		
		//Sprints
		if(input.isKeyDown(input.KEY_LSHIFT) && !sprinting)
		{
			speed++;
			sprinting = true;
		}else if(sprinting && !input.isKeyDown(input.KEY_LSHIFT))
		{
			speed --;
			sprinting = false;
		}
		
		if(input.isKeyDown(input.KEY_I))
		{
			if(autoattacking)
			{
				autoattacking = false;
			}else if(!autoattacking)
			{
				autoattacking = true;
			}
		}
		
		//Attacks
		if(input.isKeyDown(input.KEY_SPACE) || autoattacking)
		{
			if(System.currentTimeMillis() - lastTick > 1000)
			{
				atk = new ShortMelee(this, faceDir);
				attacking = true;
				lastTick = System.currentTimeMillis();
			}else
			{
				atk = null;
				attacking = false;
			}
		}else
		{
			atk = null;
			attacking = false;
		}
	}
	
	public ShortMelee getAttack()
	{
		return atk;
	}
	
	public void move(float xa, float ya)
	{
		x += xa * speed * 2;
		y += ya * speed * 2;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
}
