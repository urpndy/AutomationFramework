package vtiger.GenericUtilities;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * This class consists of Generic/reusable Methods related to property file
 * @author AdarshPandey
 * 
 */
public class PropertyFileUtility {

	/**
	 * This method will read data from property file and return the value to caller
	 * @param PropertyFilekey
	 * @return
	 * @throws Throwable
	 */
	public String readDataFromPropertyFile(String PropertyFilekey) throws Throwable
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p=new Properties();
		p.load(fis);
		String value=p.getProperty(PropertyFilekey);
		return value;
	}
}
