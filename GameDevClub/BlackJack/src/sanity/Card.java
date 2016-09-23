package sanity;

import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

public class Card {

	private int val;
	private char type;
	private boolean isVis=false;
	private Image backSprite;
	private Image frontSprite;
	private float x;
	private float y;
	
	public Card(int val, char type)
	{
		this.val=val;
		this.type=type;
		
		try {
			backSprite= new Image("Res.BaseCardBack.png");
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try {
			frontSprite= new Image("Res.BaseFrontCard.png");
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public Card( int val, int type)
	{
		this.val=val;
		if (type==1)
			this.type='H';
		else if (type==2)
			this.type='D';
		else if (type==3)
			this.type='S';
		else this.type='C';
		
		try {
			backSprite= new Image("Res.BaseCardBack.png");
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try {
			frontSprite= new Image("Res.BaseFrontCard.png");
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public Card (int val, int type, char text)
	{
		if (text!='t')
			System.out.println("ERROR: TEXT BASED CONSTRUCTOR");
		this.val=val;
		if (type==1)
			this.type='H';
		else if (type==2)
			this.type='D';
		else if (type==3)
			this.type='S';
		else this.type='C';
	}
	
	public Card(Card x)
	{
		val=x.getVal();
		type=x.getType();
		
		try {
			backSprite= new Image("Res.BaseCardBack.png");
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try {
			frontSprite= new Image("Res.BaseFrontCard.png");
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public Card(float x, float y)
	{
		Random num= new Random();
		val=num.nextInt(13)+1;
		int temp= num.nextInt(4)+1;
		if (temp==1)
			type='H';
		else if (temp==2)
			type='D';
		else if (temp==3)
			type='S';
		else type='C';
		this.x=x;
		this.y=y;
		
		try {
			backSprite= new Image("Res.BaseCardBack.png");
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try {
			frontSprite= new Image("Res.BaseFrontCard.png");
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public Card()
	{
		Random num= new Random();
		val=num.nextInt(13)+1;
		int temp= num.nextInt(4)+1;
		if (temp==1)
			type='H';
		else if (temp==2)
			type='D';
		else if (temp==3)
			type='S';
		else type='C';
		
		try {
			backSprite= new Image("Res.BaseCardBack.png");
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try {
			frontSprite= new Image("Res.BaseFrontCard.png");
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public Card(char text)
	{
		Random num= new Random();
		val=num.nextInt(13)+1;
		int temp= num.nextInt(4)+1;
		if (temp==1)
			type='H';
		else if (temp==2)
			type='D';
		else if (temp==3)
			type='S';
		else type='C';
	}
	
	public int getVal()
	{
		return val;
	}
	
	public void setXY(float x, float y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void setX(float x)
	{
		this.x=x;
	}
	
	public void setY(float y)
	{
		this.y=y;
	}
	
	public char getType()
	{
		return type;
	}
	
	@Override
	public String toString()
	{
		String suit;
		if (type=='H')
			suit="Hearts";
		else if (type=='D')
			suit="Diamonds";
		else if (type=='S')
			suit="Spades";
		else suit="Clubs";
		
		String value;
		if (val==1)
			value="Ace";
		else if (val==11)
			value="Jack";
		else if (val==12)
			value="Queen";
		else if (val==13)
			value="King";
		else value= ""+ val;
		
		return  ""+value+" of "+suit;
	}

	public boolean isVis() {
		return isVis;
	}

	public void setVis(boolean isVis) {
		this.isVis = isVis;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		if (isVis)
		{
			g.drawImage(frontSprite, x, y);
		}
		
		else g.drawImage(backSprite, x, y);
	}
}
