package com.satfnRegBot.mainRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class MainRunner {
	
	public static TestNG testNG;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testNG = new TestNG();
		
		//path of the XML file
		String path = System.getProperty("user.dir")+File.separator+"satisfactionRegistration.xml";
		
		//by using the suiteXML file
		List<String> suiteXMLs = new ArrayList<String>();
		suiteXMLs.add(path);
		testNG.setTestSuites(suiteXMLs);
		testNG.run();
		
		System.out.println(path);

	}

}
