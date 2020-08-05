package mobileTest;

//import io.appium.java_client.android.AndroidDriver;  // Appium drivers
import java.io.File; 
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;   // Library used to create URL for the Appium server
import java.util.concurrent.TimeUnit;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.touch.TouchActions;
// Libraries for configuring Desired Capabilities
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Amazon<AppiumDriver> {
	static io.appium.java_client.AppiumDriver<MobileElement> driver; 
	 
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		Amazon obj = new Amazon();		
		obj.Setup();
		obj.AmazonLogin();
		Thread.sleep(8000);
		obj.AmazonSearch();
		Thread.sleep(8000);
		obj.AmazonVSwipe();
		Thread.sleep(8000);
		obj.AmazonSelect();
		Thread.sleep(8000);
		obj.AmazonScroll();
		Thread.sleep(8000);
		obj.AmazonEnd();
		Thread.sleep(8000);
		obj.AmazonPurchase();
		}
		public static void Setup() throws MalformedURLException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "OnePlus A5000");
		capabilities.setCapability("udid", "6cad852f");
		capabilities.setCapability("platformVersion", "10.0.0");
		capabilities.setCapability("platformName", "Android");
		// for getting details use command in cmd :dumpsys window windows | grep -E ‘mCurrentFocus|mFocusedApp’
		//capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
		capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

		// Constructor to initialize driver object with new Url and Capabilities with host 4723 default host
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
public static void AmazonLogin() throws MalformedURLException, InterruptedException{
	driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	Thread.sleep(3000);
	driver.findElementByXPath("//*[@text = 'Already a customer? Sign in']").click();
	driver.findElementByClassName("android.widget.EditText").sendKeys("9410573227");
	driver.findElementByClassName("android.widget.Button").click();
	Thread.sleep(3000);
	driver.findElementByClassName("android.widget.EditText").sendKeys("password2020");
Thread.sleep(3000);
driver.findElementByClassName("android.widget.Button").click();
			}

public static void AmazonSearch() throws InterruptedException {
	driver.findElementByClassName("android.widget.EditText").click();
Thread.sleep(3000);
		driver.findElementByClassName("android.widget.EditText").sendKeys("65-inch TV");
		Thread.sleep(3000);
		driver.findElementByClassName("android.widget.EditText").sendKeys(Keys.RETURN);
			}

	public static void AmazonVSwipe() {
		//runtime Screen Size can be handle
		Dimension dim = driver.manage().window().getSize();
		int height = dim.height;
		int width = dim.width;
		int x = width/2; // just to take middle of line in x axis
		int starty = (int)(height*0.80);  // vertical
		int endy = (int)(height*0.20);
		
		TouchAction action = new TouchAction(driver);
		
		action.longPress(PointOption.point(x, starty)).moveTo(PointOption.point(x, endy)).release().perform();
		//action.press(x, starty).moveTo(x, endy).release().perform ();
		//action.tap(x, starty).waitAction(1000).moveTo(x,endy).release().perform();
				}
	public static void AmazonScroll() {
		TouchActions actions = new TouchActions(driver);
		actions.scroll(10, 100).perform();
				}
	
	public static void AmazonSelect(){
		driver.findElementByXPath
		("//*[@text = 'Samsung 163 cm (65 Inches) 4K Ultra HD Smart LED TV UA65RU7100KXXL (Black) (2019 Model)']").click();
	String Title = driver.getTitle();
	MobileElement Price = driver.findElementByXPath("//*[@index = '0']");
	String PPrice = Price.getText();
	
	Assert.assertEquals(Title, "Samsung 163 cm (65 Inches) 4K Ultra HD Smart LED TV UA65RU7100KXXL (Black) (2019 Model)");
	Assert.assertEquals(PPrice, "9509");
	}
	public static void AmazonPurchase() {
		driver.findElementByXPath("//*[@text = 'Add to Cart']").click(); // add to cart
		driver.findElementById("com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_count").click();
		driver.findElementByXPath("//*[@text = 'Proceed to Buy']").click();
		driver.findElementById("a-autoid-0-announce").click();
		driver.findElementByXPath("//*[@text = 'Continue']").click();
	}

	public static void AmazonEnd() throws InterruptedException {
		driver.findElementById("com.amazon.mShop.android.shopping:id/chrome_action_bar_home_logo");// Home Page navigation
		driver.findElementById("com.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon").click();
		
		Thread.sleep(5000);
		driver.quit();
	}
	
}
