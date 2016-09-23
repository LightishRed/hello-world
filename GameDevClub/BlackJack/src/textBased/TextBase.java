package textBased;

import sanity.*;
import javax.swing.JOptionPane;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TextBase {

	private static final int SIZE=56;
	private static final int CAP=21;
	private static final int MAX=999;
	
	public static void main(String[] args) throws IOException
	{
		Object[] build= {"Default Deck", "Randomly Generated Deck"};
		ArrayList<Card> pile= new ArrayList<Card>();
		
		JOptionPane.showMessageDialog(null, "Welcome to BlackJack");
		
		int deck= JOptionPane.showOptionDialog(null, "Would you like to use"
				+ " a default deck or a randomly generated deck?", "Welcome",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
				build, build[0]);
		
		if (deck==0)
		{
			for (int loop=1; loop<5; loop++)
			{
				for (int i=1; i<14; i++)
				{
					pile.add(new Card(i, loop, 't'));
				}
			}
		}
		else while (pile.size()<=SIZE)
		{
			pile.add(new Card('t'));
		}
		
		int total=0;
		int NPC=0;
		int human=0;
		while ((total<2)||(total>4))
		{
			Object[] numbers= new Object[]{1, 2, 3, 4};
			human=JOptionPane.showOptionDialog(null, "Please select the number of human"
					+ " players", "Humans", JOptionPane.INFORMATION_MESSAGE, 
					JOptionPane.DEFAULT_OPTION, null, numbers, numbers[0])+1;
			
			if (human==4)
			{
				numbers= new Object[]{0};
			}
			if (human==3)
			{
				numbers= new Object[]{0, 1};
			}
			if (human==2)
			{
				numbers= new Object[]{0, 1, 2};
			}
			if (human==1)
			{
				numbers= new Object[]{0, 1, 2, 3};
			}
				
			NPC= JOptionPane.showOptionDialog(null, "Please select the number of NPC "
					+ "players","Players", JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.DEFAULT_OPTION, null, numbers, numbers[0]);
			total=human+NPC;
		}
		
		//debug(pile);
		Collections.shuffle(pile);
		//debug(pile);
		
		ArrayList<Player> people= new ArrayList<Player>();
		
		int replay=0;
		
		while (replay==0)
		{
			for (int loop=0; loop<human; loop++)
			{
				people.add(new Player());
				people.get(loop).setCode(loop+1);
				people.get(loop).hit(pile);
				people.get(loop).hit(pile);
			}
			
			for (int loop=0; loop<NPC; loop++)
			{
				people.add(new Comp());
				people.get(human+loop).setCode(human+loop+1);
				people.get(human+loop).hit(pile);
				people.get(human+loop).hit(pile);
				//debug(people.get(loop));
			}
			boolean done=false;
			while (!done)
			{
				/*Object[] who= new Object[human];
				for (int i=0; i<human; i++)
				{
					who[i]=i;
				}*/
				
				done=true;
				for (Player person: people)
				{
					if (person.isNPC()) 
					{
						if (person.isHit(people))
						{
							person.hit(pile);
							try {
								person.isBlack();
							} catch (GameOverException e){
								person.setCode(3);
								done=true;
								break;
							}
							if (person.isBust())
							{
								try {
									person.busted();
								} catch (GameOverException e) {
									person.setCode(2);
								}
							}
						}
					}
					
					else {
						if (person.getCond()==0)
						{
							int temp= playerStep(people, person, pile);
							if (temp==0)
								done=false;
							if (temp==3)
							{
								done=true;
								break;
							}
						}
					}
				}
			}
			replay=endGame(people, pile);
			for (int i=0; i<people.size(); i++)
			{
				pile.addAll(people.get(i).endGame());
			}
			people.clear();
			Collections.shuffle(pile);
		}
	}
	
	public static void displayBust(Player person)
	{
		String mes= "Oh no! Player "+person.getCode()+" has gone bust!";
		JOptionPane.showMessageDialog(null, mes);
	}
	
	public static int endGame(ArrayList<Player> people, ArrayList<Card> pile)
	{
		int replay=0;
		Object[] opt= {"Play Again?", "End Game."};
		int[] winner= new int[2];
		String end="";
		boolean tie=false;
		
		for (int loop=0; loop<people.size(); loop++)
		{
			if (people.get(loop).getCond()==3)
			{
				if ((!tie)&&(winner[0]==CAP))
				{
					int temp=winner[1];
					winner= new int[3];
					winner[0]=CAP;
					winner[1]=temp;
					winner[2]=loop;
					tie=true;
				}
				else if (tie&&(winner[0]==CAP))
				{
					int[] temp=winner;
					winner= new int[4];
					winner[0]=CAP;
					winner[1]=temp[0];
					winner[2]=temp[1];
					winner[3]=loop;
				}
				
				else
				{
					winner[1]=loop;
					winner[0]=CAP;
				}
			}
			
			if ((people.get(loop).getCond()==1)&&(people.get(loop).getVal()>winner[0]))
			{
				winner= new int[2];
				winner[0]=people.get(loop).getVal();
				winner[1]=loop;
				tie=false;
			}
			
			else if ((people.get(loop).getCond()==1)&&(people.get(loop).getVal()==winner[0]))
			{
				if (!tie)
				{
					int temp=winner[1];
					winner= new int[3];
					winner[0]=people.get(loop).getVal();
					winner[1]=temp;
					winner[2]=loop;
					tie=true;
				}
				
				else if (tie)
				{
					int[] temp= winner;
					winner= new int[4];
					winner[0]= temp[0];
					winner[1]= temp[1];
					winner[2]= temp[2];
					winner[3]= loop;
				}
			}
		}
		
		for (int loop=1; loop<winner.length; loop++)
		{
			if (people.get(winner[loop]).isNPC())
			{
				end= end+ "NPC Player "+(winner[loop]+1);
			}
			
			else end= end+ "Player "+( winner[loop]+1);
			
			if (tie)
			{
				end= end+" tied with "+ people.get(winner[loop]).toString(1);
			}
			
			else end= end+ " won with "+ people.get(winner[loop]).toString(1);
		}
		
		for (int loop=0; loop<people.size(); loop++)
		{
			int count=winner.length;
			if ((count==2)&&(winner[1]==loop))
				end= end +'\n';
			else if ((count==3)&&(winner[1]==loop)||(winner[2]==loop))
				end= end+'\n';
			else if ((count==4)&&(winner[1]==loop)||(winner[2]==loop)||(winner[3]==loop))
				end= end+'\n';
			else 
			{
				if (people.get(loop).isNPC())
					end= end+ "NPC Player "+(loop+1)+" had "+people.get(loop).toString(1)+'\n';
				else
					end= end+ " Player "+(loop+1)+" had "+people.get(loop).toString(1);
			}
		}
		
		replay= JOptionPane.showOptionDialog(null, end, "Play Again?", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				opt, opt[0]);
		return replay;
	}
	
	/*public static int endGame(ArrayList<Player> people, ArrayList<Card> pile) throws IOException
	{
		int replay=0;
		Object[] opt= {"Play again?", "End game."};
		String result="";
		if (cond==2)
			{
				result= "BLACKJACK!"+'\n'+"Player "+(win+1)+"! You won with "+
						people.get(win).toString(1);
			}
		if (cond==3)
			{
				result="BUST"+'\n'+"Player lost with "+people.get(0).toString(1);
			}
		if (cond==1)
		{
			boolean won=true;
			boolean done=false;
			int m=0;
			for (int l=0; l<people.size(); l++)
			{
				if (people.get(l).isNPC())
					m++;
			}
			
			while (!done)
			{
				done=true;
				for (int i=m; i<people.size(); i++)
				{
					if (people.get(i).isHit(people))
					{
						people.get(i).hit(pile);
						done=false;
					}
					
					if (people.get(i).isBust())
						people.get(i).busted();
					
					if (people.get(i).isBlack())
					{
						cond=5;
						win=i;
						done=true;
					}
				}
			}
			for (int i=m; i<people.size(); i++)
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
		
		replay= JOptionPane.showOptionDialog(null, result, "Play Again?", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, opt, opt[0]);
		for (int i=0; i<people.size(); i++)
		{
			pile.addAll(people.get(i).endGame());
		}
		people.clear();
		Collections.shuffle(pile);
		return replay;
	}*/
	
	public static int playerStep(ArrayList<Player> people, Player person, ArrayList<Card> pile)
	{
		JOptionPane.showMessageDialog(null, "Everyone except for Player "
				+(person.getCode())+" avert your eyes!");
		
		/*if (person.isBust())
		{
			JOptionPane.showMessageDialog(null, "Oh no! You have gone bust with"+'\n'+
					person.personalString());
			return 2;
		}*/
		
		Object[] play= {"Hit", "Stay"};
		String view=person.personalString();
		for (int loop=0; loop<people.size(); loop++)
		{
			if (loop!=(person.getCode()-1))
				view=view+"Player "+(loop+1)+people.get(loop).toString()+'\n';
		}
		view=view+"Do you want to hit or stay?";
		
		int draw=JOptionPane.showOptionDialog(null, view, "Play", 
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
			null, play, play[0]);
		
		if (draw==0)
		{
			try 
			{	
				person.hit(pile);
			
				JOptionPane.showMessageDialog(null, person.personalString());
			
				if (person.isBlack())
				{
					person.setCond(3);
				}
				
				if (person.isBust())
				{
					person.busted();
				}
			} catch (GameOverException e)
			{
				if (e.getVal()==21)
				{
					person.setCond(3);
					return 3;
				}
				
				if (e.getVal()==0)
				{
					person.setCond(2);
					return 2;
				}
			}
		}
		else
		{
			person.setCond(1);
			return 1;
		}
		return 0;
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
		JOptionPane.showMessageDialog(null, x.debug());
	}
	
	public void store()
	{
		/*if (people.get(i).isBlack())
		people.get(i).setCond(3);
	
		if (person.isNPC())
		{
			if (((person.getCond()==0))&&(playerStep(people, i, pile)==0))
				done=false;
		}
	
		else if (people.get(i).isHit(people))
		{
			people.get(i).hit(pile);
			if (people.get(i).isBust())
			{
				//people.get(i).busted();
				people.get(i).setCond(2);
			}
			if (people.get(i).isBlack())
			{
				people.get(i).setCond(3);
				done=true;
			}
		}
	
		else people.get(i).setCond(1);
	
		if (people.get(i).getCond()==3)
			i=MAX;*/
		
		/*for (int m=human; m<people.size(); m++)
		{
			if (people.get(m).isHit(people))
			{
				people.get(m).hit(pile);
				if (people.get(m).isBust())
				{
					people.get(m).busted();
					people.get(m).setCond(2);
				}
				if (people.get(m).isBlack())
				{
					people.get(m).setCond(3);
					cond=5;
					win=m;
				}
			}
		}*/
	}
}
