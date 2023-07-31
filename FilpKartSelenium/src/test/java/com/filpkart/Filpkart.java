package com.filpkart;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Filpkart {
    private ChromeDriver driver;

    public Filpkart() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dasar\\Downloads\\chromedriver_win32 (5)\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void launchTest() {
        String url = "https://www.flipkart.com/";
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        boolean isFooterPresent = driver.findElements(By.cssSelector("footer")).size() > 0;
		System.out.println("FlipkartPage");
		System.out.println("BottomPage");
    }

    public void searchTest() throws IOException, InterruptedException {
        String url = "https://www.flipkart.com/";
        driver.get(url);
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("iphone 13" + Keys.ENTER);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        TakesScreenshot screenShot = (TakesScreenshot) driver;
        for (int i = 0; i < 35; i++) {
            js.executeScript("window.scrollBy(0,250)", " ");
            File source = screenShot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File("E://test-selenium/ss" + i + ".jpg");
            Files.copy(source, destinationFile);
            System.out.println("Taking Screen Shot");
            if (source != null) {
                System.out.println("Image is downloaded just before the user scrolls to its position and gets displayed in time");
            } else {
                System.out.println("Image is not downloaded");
            }
        }
        for (int i = 0; i < 35; i++) {
            js.executeScript("window.scrollBy(0,-250)", " ");
            File source = screenShot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File("E://test-selenium/Scrollss" + i + ".jpg");
            Files.copy(source, destinationFile);
            System.out.println("Taking Screen Shot");
            if (source != null) {
                System.out.println("Image is downloaded just before the user scrolls to its position and gets displayed in time");
            } else {
                System.out.println("Image is not downloaded");
            }
        }
		System.out.println("searchTest Method");

    }

    public void testPageScrollFeatureTest() {
        String url = "https://flipkart.com";
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long totalPageHeight = (Long) js.executeScript("return Math.max( document.body.scrollHeight, document.body.offsetHeight, document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight );");
        long viewportHeight = (Long) js.executeScript("return window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;");
        boolean hasScrollFeature = totalPageHeight > viewportHeight;

		System.out.println("testPageScrollFeatureTest Method");

    }

    public void performancetestForLaunchingPageTest() {
        String url = "https://www.flipkart.com";
        driver.get(url);
        long startTime = System.currentTimeMillis();
        long waitTimeInMillis = 5000;
        try {
            Thread.sleep(waitTimeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long pageLoadTimeInMillis = endTime - startTime;
        System.out.println("Page Load Time: " + pageLoadTimeInMillis + " milliseconds");
		System.out.println("performancetestForLaunchingPageTest Method");

    }

    public void performanceTestingForSearchProductTest() {
        String url = "https://www.flipkart.com";
        driver.get(url);
        WebElement search = driver.findElement(By.name("q"));
        System.out.println("search Element " + search);
        search.sendKeys("iphone 13" + Keys.ENTER);
        long startTime = System.currentTimeMillis();
        long waitTimeInMillis = 2000;
        try {
            Thread.sleep(waitTimeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long pageLoadTimeInMillis = endTime - startTime;
        System.out.println("Page Load Time: " + (pageLoadTimeInMillis - waitTimeInMillis) + " milliseconds");
		System.out.println("performanceTestingForSearchProductTest Method");

    }

    public void visibleImagesWithinHeightTest() {
        String url = "https://www.flipkart.com/search?q=iphone%2013&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=off&as=off";
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> imageElements = driver.findElements(By.className("_396cs4"));
        if (!imageElements.isEmpty()) {
            System.out.println("Total Images Found: " + imageElements.size());
            long viewportHeight = (long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight;");
            for (int i = 0; i < imageElements.size(); i++) {
                js.executeScript("window.scrollBy(0,250)", " ");
                WebElement imageElement = imageElements.get(i);
                boolean isLoadedAndVisible = (boolean) ((JavascriptExecutor) driver).executeScript(
                        "var rect = arguments[0].getBoundingClientRect();\n" +
                                "return (rect.top >= 0 && rect.bottom <= " + viewportHeight + ");", imageElement);
                if (isLoadedAndVisible) {
                    System.out.println("Image #" + (i + 1) + " is loaded and visible within the screen height.");
                    String altText = imageElement.getAttribute("alt");
                    System.out.println("Alt Text: " + altText);
                    String srcURL = imageElement.getAttribute("src");
                    System.out.println("Image Source URL: " + srcURL);
                } else {
                    System.out.println("Image #" + (i + 1) + " is not loaded or not visible within the screen height.");
                }
            }
        } else {
            System.out.println("No images found with the class name _396cs4.");
        }
		System.out.println("visibleImagesWithinHeightTest Method");

    }

    public void pageHasAScrollFeatureTest() {
        String url = "https://www.flipkart.com/search?q=iphone%2013&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=off&as=off";
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long pageHeight = (long) ((JavascriptExecutor) driver).executeScript("return Math.max( document.body.scrollHeight, " +
                "document.body.offsetHeight, document.documentElement.clientHeight, document.documentElement.scrollHeight, " +
                "document.documentElement.offsetHeight );");
        long viewportHeight = (long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight;");
    
		System.out.println("pageHasAScrollFeatureTest Method");

    }

    public void bottomPageTest() throws InterruptedException {
        String url = "https://www.flipkart.com/search?q=iphone%2013&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=off&as=off";
        driver.get(url);
        Thread.sleep(4000);
        WebElement elements = driver.findElement(By.className("_3-dnWo"));

        System.out.println(elements.getText());
        Actions action = new Actions(driver);
        action.scrollToElement(elements);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(4000);
    System.out.println("Bottom page");
        
		System.out.println("bottomPageTest Method");

    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Filpkart filpkart = new Filpkart();

        filpkart.launchTest();
        filpkart.searchTest();
        filpkart.testPageScrollFeatureTest();
        filpkart.performancetestForLaunchingPageTest();
        filpkart.performanceTestingForSearchProductTest();
        filpkart.visibleImagesWithinHeightTest();
        filpkart.pageHasAScrollFeatureTest();
        filpkart.bottomPageTest();

        filpkart.tearDown();
    }
}

