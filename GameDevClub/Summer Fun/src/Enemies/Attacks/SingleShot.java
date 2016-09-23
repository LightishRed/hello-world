package Enemies.Attacks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Enemies.Spawn;
import Player.BasicPlayer;

public class SingleShot extends BasicProjectile
{
	Image sprite;

	public SingleShot(Spawn attacker, BasicPlayer target, float speed) 
	{
		super(attacker, target, speed);
	}

	@Override
	public void render(GameContainer gc, Graphics g, Image sprite) throws SlickException {
		this.sprite = sprite;
		
		sprite.draw(x, y);
	}
	
	public void tick(int delta)
	{
		super.tick();
	}
}
