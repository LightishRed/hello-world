package sanity;

import java.util.ArrayList;
import java.util.Collections;

//import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Table {

	private Image sprite;
	private ArrayList<Card> pile;
	private ArrayList<Player> people;
	private final static int SIZE=52;;
	private boolean listen=false;
	private final static int CAP=21;
	
	public Table(ArrayList<Card> pile, ArrayList<Player> people)
	{
		this.pile=pile;
		this.people=people;
		try {
			sprite= new Image("Res/Table.png");
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Card> getPile()
	{
		return pile;
	}
	
	/*public void checkHit(int opt)
	{
		if (opt==0)
			hit(people.get(0));
		for (int loop=1; loop<people.size(); loop++)
		{
			if (people.get(loop).isHit(people))
				hit(people.get(loop));
		}
		if (protagCond()==1)
			listen=true;
	}*/
	
	public void hit(Player y)
	{
		Card temp= pile.remove(0);
		if (y.getCode()==1)
		{
			temp.setXY(y.getHand().size()*5+400, 600);
		}
		
		else if (y.getCode()==2)
		{
			temp.setXY(y.getHand().size()*5+400, 200);
		}
		
		else if (y.getCode()==3)
		{
			temp.setXY(200, y.getHand().size()*5+400);
		}
		
		else {
			temp.setXY(600, y.getHand().size()*5+400);
		}
		
		y.checkVis();
		y.addCard(temp);
	}
	
	/*public int protagCond()
	{
		if (people.get(0).isBust())
			return 3;
		if (people.get(0).isBlack())
			return 2;
		return 1;
	}*/
	
	/*public int[] endCond(int play)
	{
		int[] winner= {0, play};
		for (int m=1; m<people.size(); m++)
		{
			if (people.get(m).isHit(people))
			{
				people.get(m).hit(pile);
				if (people.get(m).isBust())
				{
					people.get(m).busted();
				}
				if (people.get(m).isBlack())
				{
					winner[0]=m;
					winner[1]=5;
				}
			}
		}
		return winner;
	}*/
	
	/*public void endGame(int cond, int win, int replay)
	{
		String result="";
		if (cond==2)
			{
				result= "BLACKJACK!"+'\n'+"You won with "+people.get(0).toString(1);
			}
		if (cond==3)
			{
				result="BUST"+'\n'+"You lost with "+people.get(0).toString(1);
			}
		if (cond==1)
		{
			boolean won=true;
			for (int m=1; m<people.size(); m++)
			{
				checkHit(1);
			}
			for (int i=1; i<people.size(); i++)
			{
				if ((CAP-people.get(0).getVal())>(CAP-people.get(i).getVal()))
						won=false;
				if (!won&&(CAP-people.get(i-1).getVal())>CAP-people.get(i).getVal())
				{
					win=i;
				}
			}
			if (won)
				{
					result="You won with "+people.get(0).toString(1);
					for (int loop=1; loop<people.size(); loop++)
					{
						result=result+'\n'+"Player "+(loop+1)+" had "+people.get(loop).toString(1);
					}
				}
		}
		
		if ((cond==5)||(win!=0))
		{
			result= "You lost. Player "+(win+1)+" won with "+people.get(win).toString(1);
			result= result+'\n'+"You had "+people.get(0).toString(1);
			for (int loop=1; loop<people.size(); loop++)
			{
				if (loop!=win)
					result=result+'\n'+"Player "+(loop+1)+" had "+people.get(loop).toString(1);
			}
		}
		
		for (int i=0; i<people.size(); i++)
		{
			pile.addAll(people.get(i).endGame());
		}
		Collections.shuffle(pile);
	}*/
	
	/*public int protagCond()
	{
		if (people.get(0).isBust())
			return 3;
		if (people.get(0).isBlack())
			return 2;
		return 1;
	}*/
	
	/*public int[] endCond(int play)
	{
		int[] winner= {0, play};
		for (int m=1; m<people.size(); m++)
		{
			if (people.get(m).isHit(people))
			{
				people.get(m).hit(pile);
				if (people.get(m).isBust())
				{
					people.get(m).busted();
				}
				if (people.get(m).isBlack())
				{
					winner[0]=m;
					winner[1]=5;
				}
			}
		}
		return winner;
	}*/
	
	/*public void endGame(int cond, int win, int replay)
	{
		String result="";
		if (cond==2)
			{
				result= "BLACKJACK!"+'\n'+"You won with "+people.get(0).toString(1);
			}
		if (cond==3)
			{
				result="BUST"+'\n'+"You lost with "+people.get(0).toString(1);
			}
		if (cond==1)
		{
			boolean won=true;
			for (int m=1; m<people.size(); m++)
			{
				checkHit(1);
			}
			for (int i=1; i<people.size(); i++)
			{
				if ((CAP-people.get(0).getVal())>(CAP-people.get(i).getVal()))
						won=false;
				if (!won&&(CAP-people.get(i-1).getVal())>CAP-people.get(i).getVal())
				{
					win=i;
				}
			}
			if (won)
				{
					result="You won with "+people.get(0).toString(1);
					for (int loop=1; loop<people.size(); loop++)
					{
						result=result+'\n'+"Player "+(loop+1)+" had "+people.get(loop).toString(1);
					}
				}
		}
		
		if ((cond==5)||(win!=0))
		{
			result= "You lost. Player "+(win+1)+" won with "+people.get(win).toString(1);
			result= result+'\n'+"You had "+people.get(0).toString(1);
			for (int loop=1; loop<people.size(); loop++)
			{
				if (loop!=win)
					result=result+'\n'+"Player "+(loop+1)+" had "+people.get(loop).toString(1);
			}
		}
		
		for (int i=0; i<people.size(); i++)
		{
			pile.addAll(people.get(i).endGame());
		}
		Collections.shuffle(pile);
	}*/

	public void buildDeck(int deck)
	{
		/*Object[] options= {"Default Deck", "Randomly Generated Deck"};
		int deck=JOptionPane.showOptionDialog(null, "Welcome to Blackjack"+'\n'+
				"Do you wish to use a default deck, or a randomly generated one?",
				"Welcome", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);*/
		pile.clear();
		if (deck==0)
		{
			for (int loop=1; loop<5; loop++)
			{
				for (int i=1; i<14; i++)
				{
					pile.add(new Card(i, loop));
					pile.get(loop*i).setXY(400, 400);
				}
			}
		}
		else while (pile.size()<=SIZE)
		{
			pile.add(new Card());
			pile.get(pile.size()-1).setXY(400, 400);
		}
		listen=true;
	}
	
	public void buildPlayers(int choice)
	{
		people.clear();
		people.add(new Player());
		people.get(0).setCode(1);
		
		/*Object[] numbers= {2 , 3, 4};
		int choice= JOptionPane.showOptionDialog(null, "Please select the number of players",
				"Players", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,
				null, numbers, numbers[0])+2;*/
		
		for (int loop=1; loop<choice; loop++)
		{
			people.add(new Comp());
			people.get(loop).setCode(loop+1);
			people.get(loop).hit(pile);
			people.get(loop).hit(pile);
		}
		listen=true;
	}
	
	public void setListen(boolean x)
	{
		listen=x;
	}
	
	public boolean isListen()
	{
		return listen;
	}
	
	public void tick( long delta)
	{
		
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawImage(sprite, 400, 400);
		for (Player person : people)
		{
			person.render(gc, g);
		}
		
		pile.get(0).render(gc, g);
	}
}
