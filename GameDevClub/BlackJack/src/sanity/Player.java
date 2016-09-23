package sanity;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Player {

	private static final int CAP=21;
	protected int currentVal;
	private ArrayList<Card> hand= new ArrayList<Card>();
	private boolean aceHigh=true;
	private boolean bust=false;
	private int code;
	private int cond=0;
	
	public Player(Card x, Card y)
	{
		hand.add(x);
		hand.add(y);
		if (hand.get(0).getVal()>10)
			currentVal=10;
		else currentVal=hand.get(0).getVal();
		if (hand.get(1).getVal()>10)
			currentVal=currentVal+10;
		else currentVal=currentVal+hand.get(1).getVal();
	}
	
	public Player()
	{
		currentVal=0;
	}
	
	public ArrayList<Card> endGame()
	{
		ArrayList<Card> temp= new ArrayList<Card>(hand);
		hand.clear();
		return temp;
	}
	
	public void addCard(Card c)
	{
		hand.add(c);
		if (c.getVal()==1)
		{
			if (aceHigh)
			{
				if ((currentVal+11)>CAP)
				{
					currentVal=currentVal+1;
					aceHigh=false;
					for (Card temp : hand)
					{
						if (temp.getVal()==1)
							currentVal=currentVal-10;
					}
				}
				else currentVal=currentVal+11;
			}
		}
		else currentVal= currentVal+c.getVal();
	}
	
	public int getVal()
	{
		return currentVal;
	}
	
	public void setAce(boolean x)
	{
		aceHigh=x;
	}
	
	public boolean isBust()
	{
		if (currentVal>CAP)
			bust=true;
		return bust;
	}
	
	public boolean isBlack() throws GameOverException
	{
		if (currentVal==CAP)
			throw new GameOverException(currentVal);
		return false;
	}
	
	public ArrayList<Card> visible()
	{
		ArrayList<Card> vis= new ArrayList<Card>();
		for (int loop=1; loop<hand.size(); loop++)
		{
			vis.add(hand.get(loop));
		}
		if (bust)
			vis.clear();
		return vis;
	}
	
	public boolean isHit(ArrayList<Player> x)
	{
		return false;
	}
	
	public boolean isNPC()
	{
		return false;
	}
	
	public boolean isHit(int opt)
	{
		if (opt==0)
			return true;
		return false;
	}
	
	public ArrayList<Card> getHand()
	{
		return hand;
	}
	
	public void busted() throws GameOverException
	{
		int temp= currentVal;
		currentVal=0;
		throw new GameOverException(temp);
	}
	
	public void checkVis()
	{
		for (int loop=0; loop<hand.size(); loop++)
		{
			hand.get(loop).setVis(true);
		}
	}
	
	public void setCode(int i)
	{
		code=i;
	}
	
	public int getCode()
	{
		return code;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		for (Card card: hand)
		{
			card.render(gc, g);
		}
	}
	
	public void setCond(int i)
	{
		cond=i;
	}
	
	public int getCond()
	{
		return cond;
	}
	
	public String debug()
	{
		String res= "";
		for (int loop=0; loop<hand.size(); loop++)
			res=res+hand.get(loop).toString()+'\n';
		return res;
	}
	
	@Override
	public String toString()
	{
		String result=" has visible: The ";
		for (int loop=0; loop<visible().size(); loop++)
		{
			if ((loop>0)&&(loop<getHand().size()-1))
				result=result+", the ";
			if (loop==getHand().size()-1)
				result= result+" and the ";
			result= result+getHand().get(loop).toString();
		}
		
		if (isBust())
		{
			result= " has gone bust.";
		}
		
		return result;
	}
	
	public String personalString()
	{
		String result="";
		result= result+"You have the ";
		if (hand.size()>0)
		{
			for (int loop=0; loop<hand.size(); loop++)
			{
				if ((loop>0)&&(loop<hand.size()-1))
					result=result+", the ";
				if (loop==hand.size()-1)
					result= result+" and the ";
				result= result+hand.get(loop).toString();
			}
		}
		
		result=result+'\n'+" For a total value of "+currentVal+'\n';
		
		return result;
	}
	
	public void hit(ArrayList<Card> pile)
	{
		Card temp= pile.remove(0);
		if (getCode()==1)
		{
			temp.setXY(getHand().size()*5+400, 600);
		}
		
		else if (getCode()==2)
		{
			temp.setXY(getHand().size()*5+400, 200);
		}
		
		else if (getCode()==3)
		{
			temp.setXY(200, getHand().size()*5+400);
		}
		
		else {
			temp.setXY(600, getHand().size()*5+400);
		}
		
		checkVis();
		addCard(temp);
	}
	
	public String toString(int y)
	{
		String result="the ";
		if (bust)
		{
			result="gone bust with ";
		}
		for (int loop=0; loop<hand.size(); loop++)
		{
			if ((loop>0)&&(loop<hand.size()-1))
				result=result+", the ";
			if (loop==hand.size()-1)
				result= result+" and the ";
			result=result+hand.get(loop).toString();
		}
		if (!bust)
			result=result+ " with a total value of "+currentVal;
		if (bust)
		{
			int temp=0;
			for (int i=0; i<hand.size(); i++)
			{
				if (hand.get(i).getVal()>10)
					temp=temp+10;
				else temp=temp+hand.get(i).getVal();
			}
			result= result+ " with a total value of "+temp;
		}
		return result;
	}
}
