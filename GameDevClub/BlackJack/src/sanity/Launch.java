package sanity;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Launch {

	public static void main(String[] args)
	{
		AppGameContainer appgc;
		try
		{
			appgc= new AppGameContainer(new Dealer("Blackjack"));
			appgc.setDisplayMode(800, 800, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Dealer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
