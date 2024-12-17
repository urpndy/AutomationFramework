package vtiger.GenericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class consists of re usable methods related to java
 * @author AdarshPandey
 * 
 */
public class JavaUtility {
	
	/**
	 * This method will return a random number for every execution
	 * @return
	 */
	public int getRandomNumber() {
		Random r=new Random();
		return r.nextInt(1000);
	}

	/**
	 * This method will generate the current system date in specified format
	 * @return
	 */
	public String getSystemDate()
	{
		Date d=new Date();
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
		String date = formatter.format(d);
		return date;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
