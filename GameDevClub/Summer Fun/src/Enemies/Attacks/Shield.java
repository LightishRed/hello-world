package Enemies.Attacks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Enemies.Spawn;
import Player.BasicPlayer;

public abstract class Shield {

	int range;
	BasicPlayer player;
	Spawn guard;
	int tickCount;
	Image sprite;
	
	public Shield( int range, BasicPlayer player, Spawn guard)
	{
		this.range= range;
		this.player=player;
		this.guard=guard;
		tickCount = 0;
		
		try {
			sprite = new Image("res/Enemies/Guardian/Shield.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void tick()
	{
		tickCount ++;
		
		//if ((player.getAttack())&&
	}
	
	public abstract void render(GameContainer gc, Graphics g, Image sprite) throws SlickException;
}
