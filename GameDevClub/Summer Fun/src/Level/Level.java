package Level;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Enemies.Spawn;
import Player.BasicPlayer;

public class Level 
{
	ArrayList<Spawn> mobs = new ArrayList<Spawn>();
	BasicPlayer protag;
	
	public Level(BasicPlayer player)
	{
		protag = player;
	}
	
	public ArrayList<Spawn> getMobs()
	{
		return mobs;
	}
	
	public void addSpawn(Spawn mob)
	{
		mobs.add(mob);
	}
	
	public void removeSpawn(Spawn mob)
	{
		mobs.remove(mob);
	}
	
	public void playerAdvance(BasicPlayer player)
	{
		protag = player;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		for(Spawn mob : mobs)
		{
			mob.render(gc, g);
		}
		
		protag.render(gc, g);
	}
	
	public void tick(long delta)
	{
		for(Spawn mob : mobs)
		{
			mob.tick(delta);
		}
		
		protag.tick(delta);
	}
}
