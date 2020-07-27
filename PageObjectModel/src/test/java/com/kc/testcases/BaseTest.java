package com.kc.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.kc.utility.DriverFactory;
import com.kc.utility.DriverManager;

public class BaseTest {
	
	
	public WebDriver driver;
	private Properties config = new Properties();
	private FileInputStream fis ;
	
	private Logger log = Logger.getLogger(BaseTest.class);
	
	//public ExtentHtmlReporter extent_html_reporter = null;
	public static ExtentTest extent_test = null;
	
	@BeforeSuite
	public void setUp() throws IOException {
		configureLogger();
		
		
		DriverFactory.setGridPath("http://localhost:4444/wd/hub");
		System.out.println("OS name is: "+System.getProperty("os.name"));
		
		if(System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
		DriverFactory.setConfigPropertyFile(System.getProperty("user.dir")+"/src/test/resources/properties/config.properties");
		DriverFactory.setChromeDriverPath(System.getProperty("user.dir")+"/src/test/resources/executables/chromedriver");
		DriverFactory.setGeckoDriverPath(System.getProperty("user.dir")+"/src/test/resources/executables/geckodriver");
	
		}
		else {
			
			DriverFactory.setConfigPropertyFile(System.getProperty("user.dir")+"/src/test/resources/properties/config.properties");
			DriverFactory.setChromeDriverPath(System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
			DriverFactory.setGeckoDriverPath(System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
		}
		
		fis = new FileInputStream(DriverFactory.getConfigPropertyFile());
		log.info("Configuration file is loaded");
		config.load(fis);
		
		
	}
	
	public void configureLogger() {
		String log4JConfigFile = System.getProperty("user.dir")+"/src/test/resources/properties/log4j.properties";
		PropertyConfigurator.configure(log4JConfigFile);
	
	}
	
	
	public void openBrowser(String browser){
		
		DriverFactory.setIsRemote(false);
		
		if(DriverFactory.getIsRemote()==true) {
			DesiredCapabilities cap = null;
			
			if(browser.equals("firefox")) {			
				System.out.println("Browser name is: "+browser);
				cap = new DesiredCapabilities();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.MAC);
				FirefoxOptions options = new FirefoxOptions();
				options.merge(cap);
				System.out.println(cap+" firefox options");
				
			}
			
			
			else if(browser.equals("chrome")) {
				
				System.out.println("Browser name is: "+browser);
				cap = new DesiredCapabilities();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
				ChromeOptions options = new ChromeOptions();
				options.merge(cap);
				System.out.println(cap+ " chrome options");
				
			}
			
			try {
				driver = new RemoteWebDriver(new URL(DriverFactory.getGridPath()),cap);
				
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
			}
			
			
		}
		
		else {
			
			if (browser.equals("chrome")) {
				System.out.println("Launching : " + browser);
				System.setProperty("webdriver.chrome.driver",
						DriverFactory.getChromeDriverPath());
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				
				
				//ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
			//	WebDriver driver = new ChromeDriver(options);
				
				
				 driver = new ChromeDriver(options);
				//driver = new ChromeDriver();
				 
			} else if (browser.equals("firefox")) {
				System.out.println("Launching : " + browser);
				System.setProperty("webdriver.gecko.driver",
						DriverFactory.getGeckoDriverPath());
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				 driver = new FirefoxDriver(options);
			//	driver = new FirefoxDriver();

			}
			
			
			
		}
		
		DriverManager.setDriver(driver);
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//DriverManager.getDriver().get("https://www.cleartrip.com/");
		
	}
	
	public void closeBrowser() {
	//	getDriver().close();
		DriverManager.getDriver().quit();
	}
	
	

}
