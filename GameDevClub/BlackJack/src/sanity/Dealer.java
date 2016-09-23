package sanity;

import javax.swing.JOptionPane;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
//import org.newdawn.slick.state.StateBasedGame;

public class Dealer extends BasicGame {

	//private static final int CAP=21;
	private Table tab;
	private Buttons but;
	private int step=0;
	private int mouseX;
	private int mouseY;
	
	public Dealer( String title)
	{
		super(title);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException
	{
		System.out.println("Check");
		gc.setVSync(true);
		gc.setMaximumLogicUpdateInterval(60);
		
		but= new Buttons();
		
		ArrayList<Card> pile=  new ArrayList<Card>();
		pile.add(new Card());
		
		ArrayList<Player> people= new ArrayList<Player>();
		people.add(new Player(new Card(), new Card()));
		people.add(new Player(new Card(), new Card()));
		people.add(new Player(new Card(), new Card()));
		people.add(new Player(new Card(), new Card()));
		
		tab= new Table(pile, people);
	}
	
	public static void debug(ArrayList<Card> x)
	{
		String y="";
		for (int loop=0; loop<x.size(); loop++)
		{
			y=y+x.get(loop).toString()+'\n';
		}
		JOptionPane.showMessageDialog(null, y);
	}
	
	public static void debug(Player x)
	{
		JOptionPane.showMessageDialog(null, x.toString());
	}
	
	public static ArrayList<Card> shuffle(ArrayList<Card> x)
	{
		Collections.shuffle(x);
		return x;
	}
	
	public static void playerDraw(ArrayList<Card> x, Player y)
	{
		JOptionPane.showMessageDialog(null, "You drew the "+x.get(0).toString());
		y.addCard(x.remove(0));
	}
	
	/*public void runStep(int opt)
	{
		if (step==0)
		{
			tab.buildDeck(opt);
			step++;
		}
		
		else if (step==1)
		{
			tab.buildPlayers(opt+2);
			step++;
		}
		
		else if (step==2)
		{
			tab.checkHit(opt);
			if (opt==1)
				step++;
			if (tab.protagCond()!=1)
				step=3;
		}
		
		else if (step==3)
		{
			//int[] cond= tab.endCond(tab.protagCond());
			//tab.endGame(cond[1], cond[0], opt);
			if (opt==0)
			{
				step=1;
			}
			step=5;
		}
	}*/
	
	@Override
	/*public void mousePressed(int button, int x, int y)
	{
		mouseX=x;
		mouseY=y;
	}*/
	
	//@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		tab.render(gc, g);
	}
	
	@Override
	public void update(GameContainer gc, int delta)
	{
		int opt=0;
		if (step==5)
			gc.exit();
		while (tab.isListen())
		{
			int[] temp= but.render(gc, step);
			
			if ((mouseX>=temp[0])&&(mouseX<=temp[1])&&(mouseY>=temp[2])&&(mouseY<=temp[3]))
			{
				opt=0;
				tab.setListen(false);
			}
			
			else if ((mouseX>=temp[4])&&(mouseX<=temp[5])&&(mouseY>=temp[6])&&(mouseY<=temp[7]))
			{	
				opt=1;
				tab.setListen(false);
			}
			
			else if ((mouseX>=temp[8])&&(mouseX<=temp[9])&&(mouseY>=temp[10])&&(mouseY<=temp[11]))
			{	
				opt=2;
				tab.setListen(false);
			}
		}
		//runStep(opt);
	}
}
