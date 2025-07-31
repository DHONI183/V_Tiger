package Generic_Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;



/**
 * @author Manish Kumar Verma
 * @Note This class Contains property file reusable methods
 */
public class PropertyFileUtility {
	
	
	
	/**
	 * @Note : This method is used to fetch data from the property file
	 * @param key
	 * @return String
	 * @throws IOException
	 */
	public String fetchDataFromPropFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VTiger_Commondata.properties");		
		Properties p = new Properties();		
		p.load(fis);	
		fis.close();
		String data = p.getProperty(key);
		return data;		
	}
	
	
	
	
	/**
	 * @Note : This method is used to write Back data into the property file
	 * @param key
	 * @param value
	 * @param Comments
	 * @throws IOException 
	 */
	public void WriteBackDataPropFile(String key , String value ,String Comments) throws IOException {
		
		FileInputStream fis = new FileInputStream("./src/test/resources/VTiger_Commondata.properties");	
		Properties p = new Properties();
		p.load(fis);
		fis.close();
		p.put(key, value);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/VTiger_Commondata.properties");
		p.store(fos, Comments);	
		fos.close();
	}
}
