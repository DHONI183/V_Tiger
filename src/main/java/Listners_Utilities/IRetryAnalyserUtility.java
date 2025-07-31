package Listners_Utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

// changes for github
public class IRetryAnalyserUtility implements IRetryAnalyzer{

	int max = 5;
	int count = 0;
	
	public boolean retry(ITestResult result) {
		
		if(count<max) {
			count++;
			return true;
		}
		return false;		
	}
}
