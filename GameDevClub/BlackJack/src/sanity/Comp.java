package sanity;

import java.util.ArrayList;
import java.util.Random;

public class Comp extends Player{

	private int behave;
	
	public Comp()
	{
		super();
		Random be= new Random();
		behave= be.nextInt(8)+1;
	}
	
	public Comp(Card x, Card y)
	{
		super(x, y);
		Random be= new Random();
		behave= be.nextInt(8)+1;
	}
	
	public Comp(Card x, Card y, int z)
	{
		super(x,y);
		behave=z;
	}
	
	@Override
	public void checkVis()
	{
		for (int loop=1; loop<getHand().size(); loop++)
		{
			getHand().get(loop).setVis(true);
		}
	}
	
	@Override
	public boolean isNPC()
	{
		return true;
	}
	
	@Override
	public boolean isHit(ArrayList<Player> vis)
	{
		if (behave==1)
		{
			if (getVal()<18)
				return true;
		}
		
		else if (behave==2)
		{
			if (getVal()<20)
				return true;
		}
		
		else if (behave==3)
		{
			if (getVal()<15)
				return true;
		}
		
		else if (behave==4)
		{
			for (int loop=0; loop<vis.size(); loop++)
			{
				int temp=0;
				for (int i=0; i<vis.get(loop).visible().size(); i++)
				{
					temp=temp+vis.get(loop).visible().get(i).getVal();
				}
				if (((temp+7)>getVal())&&((temp+7)<24))
					return true;
			}
		}
		
		else if (behave==5)
		{
			for (int loop=0; loop<vis.size(); loop++)
			{
				int temp=0;
				for (int i=0; i<vis.get(loop).visible().size(); i++)
				{
					temp=temp+vis.get(loop).visible().get(i).getVal();
				}
				if (((temp+temp)>getVal())&&((temp+temp)<25))
					return true;
			}
		}
		
		else if (behave==6)
		{
			for (int loop=0; loop<vis.size(); loop++)
			{
				int temp=0;
				for (int i=0; i<vis.get(loop).visible().size(); i++)
				{
					temp=temp+vis.get(loop).visible().get(i).getVal();
				}
				if (((temp+3)>=getVal())&&((temp+3)<24))
					return true;
			}
		}
		
		else if (behave==7)
		{
			if (getVal()<20)
				return true;
		}
		
		else if (behave==8)
		{
			for (int loop=0; loop<vis.size(); loop++)
			{
				int temp=0;
				for (int i=0; i<vis.get(loop).visible().size(); i++)
				{
					temp=temp+vis.get(loop).visible().get(i).getVal();
				}
				if (((temp+10)>getVal())&&((temp+10)<21))
					return true;
			}
		}
		
		if (behave==21)
		{
			return false;
		}
		
		return false;
	}
	
	@Override
	public void busted() throws GameOverException
	{
		behave=21;
		int temp=currentVal;
		currentVal=0;
		
		throw new GameOverException(temp);
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
		
		if (behave==21)
		{
			result= " has gone bust.";
		}
		
		return result;
	}
	
}
