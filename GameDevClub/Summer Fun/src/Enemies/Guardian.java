package Enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Enemies.Attacks.MovingBarrier;
import Enemies.Attacks.Shield;
import Level.Level;
import Player.BasicPlayer;

public class Guardian extends Spawn {

	Image sprite;
	Image defSprite;
	boolean trig;
	boolean inRange;
	int rangeCounter=0;
	boolean block=false;
	Shield stop;
	
	public Guardian(String name,Level level, BasicPlayer player, float x, float y, float Speed) 
	{
		super(name, level, player, x, y, Speed);
		
		trig=false;
		inRange=false;
		movable=true;
		maxHealth=50;
		health=maxHealth;
		stop= new MovingBarrier(15, protag, this, 1);
		
		try {
			sprite = new Image("res/Enemies/Guardian/Guardian.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		try {
			defSprite = new Image("res/Enemies/Guardian/Shield.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(sprite, x, y);
		stop.render(gc, g, defSprite);
	}

	@Override
	public void tick(long delta) 
	{
		super.tick();
		
		if(distanceSense(100))
		{
			trig = true;
		}else
		{
			trig = false;
		}
		
		if(distanceSense(20))
		{
			inRange = true;
			trig = false;
		}
		
		move();
	}
	
	public void move()
	{
		if(trig)
		{
			follow();
		}
		
		if ((inRange)&&(!block))
		{
			if (health<20)
				health=1;
			else health=health-20;
			rangeCounter++;
		}
		
		if (rangeCounter>75)
			block=true;
		if (block)
		{
			if (health>30)
				health=maxHealth;
			else health=health+20;
			rangeCounter--;
			if (rangeCounter<=0)
				block=false;
		}
	}
}
