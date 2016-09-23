package Enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Enemies.Attacks.BasicProjectile;
import Enemies.Attacks.SingleShot;
import Level.Level;
import Player.BasicPlayer;

public class Archer extends Spawn
{
	Image sprite;
	BasicProjectile atk;
	Image atkSprite;
	
	public Archer(String name, Level level, BasicPlayer player, float x, float y, float Speed) {
		super(name, level, player, x, y, Speed);
		
		maxHealth = 50;
		health = maxHealth;
		
		try {
			sprite = new Image("res/Enemies/Archer/Archer.png");
		} catch (SlickException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		try {
			atkSprite = new Image("res/Enemies/Archer/Slimeball.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tickCount = 0;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(atk != null)
		{
			atk.render(gc, g, atkSprite);
		}
		
		g.drawImage(sprite, x, y);
	}

	@Override
	public void tick(long delta)
	{
		super.tick();
		
		if(tickCount % 100 == 0)
		{
			atk = new SingleShot(this, protag, 1);
		}
		
		if(atk != null)
		{
			atk.tick();
		}
	}

}
