package sanity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Buttons {

	private Image yes;
	private Image no;
	private Image normDeck;
	private Image rand;
	private Image hit;
	private Image stay;
	private Image two;
	private Image three;
	private Image four;
	private Graphics g;
	
	public Buttons ()
	{
		try {
			yes= new Image("Res/Yes.png");
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try {
			no= new Image("Res/No.png");
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try {
			normDeck= new Image("Res/Default.png");
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try {
			rand= new Image("Res/Random.png");
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try {
			hit= new Image("Res/Hit.png");
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try {
			stay= new Image("Res/Stay.png");
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try {
			two= new Image("Res/Two.png");
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try {
			three= new Image("Res/Three.png");
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		try {
			four= new Image("Res/Four.png");
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public void renderYes(GameContainer gc)
	{
		g.drawImage(yes, 200, 600);
	}
	
	public void renderYes(GameContainer gc, float x, float y)
	{
		g.drawImage(yes, x, y);
	}
	
	public void renderNo(GameContainer gc)
	{
		g.drawImage(no, 600, 600);
	}
	
	public void renderNo(GameContainer gc, float x, float y)
	{
		g.drawImage(no, x, y);
	}
	
	public void renderQuestion(GameContainer gc)
	{
		g.drawImage(normDeck, 200, 600);
		g.drawImage(rand, 600, 600);
	}
	
	public void renderHitStay(GameContainer gc)
	{
		g.drawImage(hit, 200, 600);
		g.drawImage(stay, 600, 600);
	}
	
	public void renderNumbers(GameContainer gc)
	{
		g.drawImage(two, 300, 400);
		g.drawImage(three, 400, 400);
		g.drawImage(four, 500, 400);
	}
	
	public int[] bound(int x)
	{
		int[] res= new int[12];
		//first two are x of option 1, second two y of option 1
		//third two x of option 2, fourth two y of option 2
		
		if (x==0)
		{
			res[0]=100;
			res[1]=300;
			res[2]=500;
			res[3]=700;
			
			res[4]=100;
			res[5]=300;
			res[6]=500;
			res[7]=700;
		}
		
		if (x==1)
		{
			res[0]=250;
			res[1]=350;
			res[2]=350;
			res[3]=450;
			
			res[4]=350;
			res[5]=450;
			res[6]=350;
			res[7]=450;
			
			res[8]=450;
			res[9]=550;
			res[10]=350;
			res[10]=450;
		}
		
		if (x==2)
		{
			res[0]=100;
			res[1]=300;
			res[2]=550;
			res[3]=650;
			
			res[4]=100;
			res[5]=300;
			res[6]=550;
			res[7]=650;
		}
		
		if (x==3)
		{
			res[0]=100;
			res[1]=300;
			res[2]=550;
			res[3]=650;
			
			res[4]=100;
			res[5]=300;
			res[6]=550;
			res[7]=650;
		}
		
		return res;
	}
	
	public int[] render(GameContainer gc, int x)
	{
		int[] contain= new int[12];
		if (x==0)
			renderQuestion(gc);
		
		if (x==1)
		
		if (x==2)
			renderHitStay(gc);
		
		if (x==3)
		{
			renderYes(gc);
			renderNo(gc);
		}
		
		contain=bound(x);
		return contain;
	}
}
