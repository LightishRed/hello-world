package Enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Level.Level;
import Player.BasicPlayer;

public class Rogue extends Spawn
{
	Image sprite;
	boolean triggered;
	boolean inRange;
	
	public Rogue(String name,Level level, BasicPlayer player, float x, float y, float Speed) {
		super(name, level, player, x, y, Speed);
		
		triggered = false;
		inRange = false;
		movable = true;
		
		maxHealth = 10;
		health = maxHealth;
		
		try {
			sprite = new Image("res/Enemies/Rogue/Rogue.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(sprite, x, y);
	}

	@Override
	public void tick(long delta) 
	{
		super.tick();
		
		if(distanceSense(100))
		{
			triggered = true;
		}else
		{
			triggered = false;
		}
		
		if(distanceSense(50))
		{
			inRange = true;
			triggered = false;
		}
		
		move();
	}
	
	public void move()
	{
		if(triggered)
		{
			follow();
		}
	}
}
