package sanity;

public class GameOverException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int value;
	
	public GameOverException(int val)
	{
		value=val;
	}
	
	public int getVal()
	{
		return value;
	}

}
