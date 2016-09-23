package Player.Attacks;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Player.BasicPlayer;

public class ShortMelee 
{
	BasicPlayer attacker;
	Animation stab;
	Image stabFrame;
	int numOfFrames = 1;
	int direction;
	
	public ShortMelee(BasicPlayer Attacker, int direction)
	{
		attacker = Attacker;
		this.direction = direction;
		
		for(int i = 1; i <= numOfFrames; i ++)
		{
			try 
			{
				stabFrame = new Image("res/Attacks/ShortMelee/Short_Melee_Attack_Frame" + i + ".png");
				
				if(direction == 1)
				{
					stabFrame.rotate(180);
				}else if(direction == 2)
				{
					stabFrame.rotate(270);
				}else if(direction == 4)
				{
					stabFrame.rotate(90);
				}
				//stab.addFrame(stabFrame, 10);
			} catch (SlickException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		//stab.draw(attacker.getX(), attacker.getY());
		
		stabFrame.draw(attacker.getX(), attacker.getY());
	}
	
	public void tick(long delta)
	{
		stab.update(delta);
	}
}
