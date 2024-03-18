package com.satfnRegBot.testRunner;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestRunner {

	public static TestNG testNG;
	public static TestListenerAdapter tla;

	public static void main(String[] args) {
		tla = new TestListenerAdapter();
		testNG = new TestNG();
		testNG.addListener(tla);
		testNG.setTestClasses(new Class[] { com.satfnRegBot.automationScripts.SatisfactionRegisteration.class });
		testNG.run();

	}

}
