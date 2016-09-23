package Game;

import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Enemies.Archer;
import Enemies.Guardian;
import Enemies.Healer;
import Enemies.Rogue;
import Enemies.Spawn;
import Enemies.Attacks.*;
import Level.Level;
import Player.BasicPlayer;

public class Game extends BasicGame
{

	BasicPlayer player;
	Rogue Daniel;
	Archer Edward;
	Healer Blaine;
	Guardian Tyler;
	Level level;
	
	public Game(String title) 
	{
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		gc.setVSync(true);
		gc.setMaximumLogicUpdateInterval(60);
		
		player = new BasicPlayer("", 500, 100);
		
		level = new Level(player);
		
		Daniel = new Rogue("Daniel", level, player, 300, 300, 3);
		Daniel.damage(5);
		level.addSpawn(Daniel);
		
		Edward = new Archer("Edward", level, player, 300, 300, 1);
		level.addSpawn(Edward);
		
		Blaine = new Healer("Blaine", level, player, 500, 500, 1);
		level.addSpawn(Blaine);
		
		Tyler = new Guardian("Tyler", level, player, 10, 45, 1);
		level.addSpawn(Tyler);
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		level.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{
		level.tick(delta);
	}

}
