package TestPack;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class test1 {

    private WebDriver driver;
    private String email;
    private String password;
    private static ExtentReports extent;
    private ExtentTest test;
    
    
    @BeforeSuite
    public void setUpSuite() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\nike9\\eclipse-workspace\\chromedriver.exe"); 
        String reportFilePath = "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\Reports\\ExtentReport1.html";
        extent = new ExtentReports(reportFilePath, true);
    }
    
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void printTranscriptAsPDF() {
        test = extent.startTest("Test Scenario 1 - Print Transcript as PDF");
        try {
            
            driver.get("https://me.northeastern.edu");
            getEmailAndPassword();
            login();
            accessTranscript();
            printAndSavePDF("C:\\Users\\nike9\\Downloads\\transcript.pdf");
            waitForSeconds(5);
            test.log(LogStatus.PASS, "Test Scenario 1 Passed");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "Test Scenario 1 Failed");
        } finally {
            extent.endTest(test);
            driver.quit();
        }
    }

    @Test(priority = 2)
    public void addingCalendarEvents() {
        test = extent.startTest("Test Scenario 2 - Adding Calendar Events");
        try {
            
            driver.get("https://northeastern.instructure.com");
            getEmailAndPassword();
            login();
            addEventsToCalendar();
            waitForSeconds(5);
            test.log(LogStatus.PASS, "Test Scenario 2 Passed");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "Test Scenario 2 Failed");
        } finally {
            extent.endTest(test);
            driver.quit();
        }
    }

    @Test(priority = 3)
    public void downloadingClassroomGuide() {
        test = extent.startTest("Test Scenario 3 - Downloading Classroom Guide");
        try {
            
            driver.get("https://service.northeastern.edu/tech?id=classrooms");
            getEmailAndPassword();
            waitForSeconds(2);
            takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 3\\Testcase3beforelogin.png");
            waitForSeconds(3);
            driver.findElement(By.linkText("Log in")).click();
            waitForSeconds(2);
            login();
            takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 3\\Testcase3afterlogin.png");
            downloadClassroomGuide();
            waitForSeconds(5);
            test.log(LogStatus.PASS, "Test Scenario 3 Passed");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "Test Scenario 3 Failed");
        } finally {
            extent.endTest(test);
            driver.quit();
        }
    }

    @Test(priority = 4)
    public void downloadingDataset() {
        test = extent.startTest("Test Scenario 4 - Downloading Dataset");
        try {
            
            driver.get("https://onesearch.library.northeastern.edu/discovery/search?vid=01NEU_INST:NU&lang=en");
            takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 4\\librarypageopened.png");
            getEmailAndPassword();
            waitForSeconds(2);
            driver.findElement(By.cssSelector("button.user-button.sign-in-btn-ctm")).click();
            login2();
            waitForSeconds(10);
            downloadDataset();
            waitForSeconds(5);
            test.log(LogStatus.PASS, "Test Scenario 4 Passed");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "Test Scenario 4 Failed");
        } finally {
            extent.endTest(test);
            driver.quit();
        }
    }

    @Test(priority = 5)
    public void academicCalendarAccess() {
        test = extent.startTest("Test Scenario 5 - Academic Calendar Access");
        try {
            driver.get("https://student.me.northeastern.edu");
            getEmailAndPassword();
            login();
            accessAcademicCalender();
            waitForSeconds(5);
            test.log(LogStatus.PASS, "Test Scenario 5 Passed");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "Test Scenario 5 Failed");
        } finally {
            extent.endTest(test);
            driver.quit();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite 
    public void tearDownSuite() {
        extent.flush();
    }
 
    
    private void accessAcademicCalender() {
		// TODO Auto-generated method stub
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 5\\beforeresourcestabclick.png");
        driver.findElement(By.cssSelector("a[data-testid='link-resources']")).click();
        waitForSeconds(5);
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 5\\resourcestab.png");
        driver.findElement(By.id("resource-tab-Academics,_Classes_&_Registration")).click();
        waitForSeconds(5);
        String search = "Academic Calendar";
        driver.findElement(By.id("SearchBox45")).sendKeys(search);
        waitForSeconds(2);
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 5\\beforeclickingAcademiCalendertbutton.png");
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[3]/section/ul/li[1]/div/a")).click();
        waitForSeconds(2);
        switchToNextTab(driver);
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 5\\CalenderwebPageButton.png");
        driver.findElement(By.cssSelector("a.__item")).click();
        waitForSeconds(5);
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 5\\Calenderwithdatespage.png");
        WebElement element = driver.findElement(By.cssSelector("#trumbaFilter"));
        waitForSeconds(5);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        waitForSeconds(15);
        driver.switchTo().frame("trumba.spud.6.iframe");
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 5\\checkboxChecked.png");
        WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox' and contains(@class, 'twCalendarListCheckbox') and @id='mixItem0']"));
        waitForSeconds(5);
          if (checkbox.isSelected()) {
              checkbox.click(); // Uncheck one of the checkboxes
          }
      	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 5\\checkboxUnchecked.png");
        waitForSeconds(5);
        driver.switchTo().defaultContent();
        
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitForSeconds(5);
      	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 5\\addtocalenderbtn.png");
      	driver.switchTo().frame("trumba.spud.2.iframe");
        String ExpectedAddtoCalenderBtnText = driver.findElement(By.xpath("//*[@id=\"ctl04_ctl100_ctl00_buttonAtmc\"]/span")).getText();
        Assert.assertEquals("Add to My Calendar", ExpectedAddtoCalenderBtnText);
        waitForSeconds(5);
        
	}
	private void downloadDataset() {
		// TODO Auto-generated method stub
    	driver.findElement(By.cssSelector("#mainMenu > div:nth-child(5)")).click();
        
    	
        waitForSeconds(5);

        switchToNextTab(driver);
        takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 4\\digitalrepoOpened.png");

        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.cssSelector("body > div:nth-child(1) > main:nth-child(5) > div:nth-child(1) > section:nth-child(2) > div:nth-child(1) > a:nth-child(6)")).click();

        waitForSeconds(5);

        takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 4\\afterclickingdatasetsbutton.png");

        driver.findElement(By.cssSelector("body > div:nth-child(1) > main:nth-child(5) > div:nth-child(2) > main:nth-child(2) > section:nth-child(2) > ul:nth-child(2) > article:nth-child(2) > div:nth-child(2) > header:nth-child(1) > h4:nth-child(1) > a:nth-child(1)")).click();
        takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 4\\afteropeningdatasetpage.png");

        waitForSeconds(5);

        driver.findElement(By.cssSelector("a[title='Zip File']")).click();
        waitForSeconds(5);
        takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 4\\dowloadedDataset.png");

        String ExpectedDatasetTitleName = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[2]/article/header/h1")).getText();
        Assert.assertEquals("120-140 GHz", ExpectedDatasetTitleName);
        waitForSeconds(10);

       // driver.quit();
	}
    
	private void login2() {
		// TODO Auto-generated method stub
    	waitForSeconds(5);
        driver.findElement(By.id("username")).sendKeys(email.substring(0, 10));
        waitForSeconds(5);
        driver.findElement(By.id("password")).sendKeys(password);
        waitForSeconds(2);
        driver.findElement(By.xpath("/html/body/section/div/div[2]/div/form/div[3]/button")).click();
        waitForSeconds(10);
	}
    
    
	private void downloadClassroomGuide() {
		// TODO Auto-generated method stub
    	
    	driver.findElement(By.cssSelector("#x77ea03d9972dd1d8beddb4221153afa6 > div > div.panel-body > span > div > div > div:nth-child(5) > div > div > div > a")).click();
        
    	waitForSeconds(2);
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 3\\afterclickingclassroomlink.png");
        String ExpectedLinkName = driver.findElement(By.cssSelector("#x51d2fa949721d518beddb4221153af23 > div > div.panel-body > span > table:nth-child(1) > tbody > tr:nth-child(1) > td:nth-child(2) > a")).getText();
        Assert.assertEquals("NUFlex Auto and Manual Classroom", ExpectedLinkName);
        driver.findElement(By.cssSelector("#x51d2fa949721d518beddb4221153af23 > div > div.panel-body > span > table:nth-child(1) > tbody > tr:nth-child(1) > td:nth-child(2) > a")).click();
        takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 3\\classroomlinkpage.png");
        waitForSeconds(2);
        switchToNextTab(driver);
        printAndSavePDF("C:\\Users\\nike9\\Downloads\\Hybrid_Nuflex_classroom.pdf");
        waitForSeconds(8);
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 3\\classroomguidedownloaded.png");
    	

	}

	private void addEventsToCalendar() {
		// TODO Auto-generated method stub
		waitForSeconds(2);
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 2\\BeforeClickingcalenderbutton.png");
    	driver.findElement(By.id("global_nav_calendar_link")).click();
    	waitForSeconds(2);
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 2\\afterClickingcalenderbutton.png");

    	 // Initialize arrays to store values
	    ArrayList<String> titles = new ArrayList<>();
	    ArrayList<String> dates = new ArrayList<>();
	    ArrayList<String> timefrom = new ArrayList<>();
	    ArrayList<String> timeto = new ArrayList<>();
	    ArrayList<String> locations = new ArrayList<>();
    	
	    try {
    	    FileInputStream file = new FileInputStream(new File("C:\\Users\\nike9\\eclipse-workspace\\datasheet.xlsx"));
    	    Workbook workbook = WorkbookFactory.create(file);

    	    Sheet sheet = workbook.getSheetAt(1);

    	   

    	    int rowCount = 0;
    	    for (Row row : sheet) {
    	        // Skip the first row (header row)
    	        if (rowCount == 0) {
    	            rowCount++;
    	            continue;
    	        }

    	        // Iterate through each cell in the row
    	        int cellCount = 0;
    	        String title = "";
    	        String date = "";
    	        String start = "";
    	        String end = "";
    	        String location = "";
    	        for (Cell cell : row) {
    	            // Check cell type
    	            switch (cell.getCellType()) {
    	                case STRING:
    	                    // Store the cell value in the respective variable
    	                    switch (cellCount) {
    	                        case 0:
    	                            title = cell.getStringCellValue();
    	                            break;
    	                        case 1:
    	                            date = cell.getStringCellValue();
    	                            break;
    	                        case 2:
    	                            start = cell.getStringCellValue();
    	                            break;
    	                        case 3:
    	                            end = cell.getStringCellValue();
    	                            break;
    	                        case 4:
    	                            location = cell.getStringCellValue();
    	                            break;
    	                    }
    	                    break;
    	                case NUMERIC:
    	                    // Handle numeric values (if necessary)
    	                    break;
    	                default:
    	                    // Handle other cell types (if necessary)
    	                    break;
    	            }
    	            cellCount++;
    	        }

    	        // Add values to arrays
    	        titles.add(title);
    	        dates.add(date);
    	        timefrom.add(start);
    	        timeto.add(end);
    	        locations.add(location);

    	        rowCount++;
    	    }

    	    // Print the stored values
    	    for (int i = 0; i < titles.size(); i++) {
    	        System.out.println("Title: " + titles.get(i));
    	        System.out.println("Date: " + dates.get(i));
    	        System.out.println("From: " + timefrom.get(i));
    	        System.out.println("To: " + timeto.get(i));
    	        System.out.println("Location: " + locations.get(i));
    	    }

    	    file.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}



        
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 2\\BeforeFillingEvent.png");
        driver.findElement(By.id("create_new_event_link")).click();
    	waitForSeconds(5);
        driver.findElement(By.id("TextInput_0")).sendKeys(titles.getFirst());
        waitForSeconds(2);      
        driver.findElement(By.id("Selectable_0")).click();
       
        	Actions a = new Actions(driver);
        	for(int b=0;b<6;b++) {
			a.keyDown(Keys.CONTROL).build().perform();
			a.keyDown(Keys.BACK_SPACE).build().perform();
            a.keyUp(Keys.CONTROL).build().perform();
            a.keyUp(Keys.BACK_SPACE).build().perform();
        	}
        driver.findElement(By.id("Selectable_0")).sendKeys(dates.getFirst());
        a.keyDown(Keys.ENTER).build().perform();
        a.keyUp(Keys.ENTER).build().perform();
        waitForSeconds(2);
        driver.findElement(By.id("Select_0")).click();
        for(int b=0;b<6;b++) {
			a.keyDown(Keys.CONTROL).build().perform();
			a.keyDown(Keys.BACK_SPACE).build().perform();
            a.keyUp(Keys.CONTROL).build().perform();
            a.keyUp(Keys.BACK_SPACE).build().perform();
        	}
    
        driver.findElement(By.id("Select_0")).sendKeys(timefrom.getFirst());
        a.keyDown(Keys.ENTER).build().perform();
        a.keyUp(Keys.ENTER).build().perform();
        waitForSeconds(2);
        driver.findElement(By.id("Select_1")).click();
        for(int b=0;b<6;b++) {
			a.keyDown(Keys.CONTROL).build().perform();
			a.keyDown(Keys.BACK_SPACE).build().perform();
            a.keyUp(Keys.CONTROL).build().perform();
            a.keyUp(Keys.BACK_SPACE).build().perform();
        	}
    
        driver.findElement(By.id("Select_1")).sendKeys(timeto.getFirst());
        a.keyDown(Keys.ENTER).build().perform();
        a.keyUp(Keys.ENTER).build().perform();
        waitForSeconds(3);
        driver.findElement(By.id("Select_4")).click();
        
        a.keyDown(Keys.ARROW_DOWN).build().perform();
        a.keyUp(Keys.ARROW_DOWN).build().perform();
        waitForSeconds(1);
        a.keyDown(Keys.ENTER).build().perform();
        a.keyUp(Keys.ENTER).build().perform();
        waitForSeconds(1);
        driver.findElement(By.id("TextInput_5")).sendKeys(locations.getFirst());
        driver.findElement(By.xpath("//button[@data-testid='edit-calendar-event-submit-button']")).click();
        waitForSeconds(15);
        
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 2\\AfterFillingEvent.png");

        driver.findElement(By.id("create_new_event_link")).click();
    	waitForSeconds(5);
        driver.findElement(By.id("TextInput_8")).sendKeys(titles.getLast());
        waitForSeconds(2);      
        driver.findElement(By.id("Selectable_6")).click();
       
      
        	for(int b=0;b<6;b++) {
			a.keyDown(Keys.CONTROL).build().perform();
			a.keyDown(Keys.BACK_SPACE).build().perform();
            a.keyUp(Keys.CONTROL).build().perform();
            a.keyUp(Keys.BACK_SPACE).build().perform();
        	}
        driver.findElement(By.id("Selectable_6")).sendKeys(dates.getLast());
        a.keyDown(Keys.ENTER).build().perform();
        a.keyUp(Keys.ENTER).build().perform();
        waitForSeconds(2);
        driver.findElement(By.id("Select_5")).click();
        for(int b=0;b<6;b++) {
			a.keyDown(Keys.CONTROL).build().perform();
			a.keyDown(Keys.BACK_SPACE).build().perform();
            a.keyUp(Keys.CONTROL).build().perform();
            a.keyUp(Keys.BACK_SPACE).build().perform();
        	}
    
        driver.findElement(By.id("Select_5")).sendKeys(timefrom.getLast());
        a.keyDown(Keys.ENTER).build().perform();
        a.keyUp(Keys.ENTER).build().perform();
        waitForSeconds(2);
        driver.findElement(By.id("Select_6")).click();
        for(int b=0;b<6;b++) {
			a.keyDown(Keys.CONTROL).build().perform();
			a.keyDown(Keys.BACK_SPACE).build().perform();
            a.keyUp(Keys.CONTROL).build().perform();
            a.keyUp(Keys.BACK_SPACE).build().perform();
        	}
    
        driver.findElement(By.id("Select_6")).sendKeys(timeto.getLast());
        a.keyDown(Keys.ENTER).build().perform();
        a.keyUp(Keys.ENTER).build().perform();
        waitForSeconds(3);
        driver.findElement(By.id("Select_9")).click();
        
        a.keyDown(Keys.ARROW_DOWN).build().perform();
        a.keyUp(Keys.ARROW_DOWN).build().perform();
        waitForSeconds(1);
        a.keyDown(Keys.ENTER).build().perform();
        a.keyUp(Keys.ENTER).build().perform();
        waitForSeconds(1);
        driver.findElement(By.id("TextInput_13")).sendKeys(locations.getLast());
        driver.findElement(By.xpath("//button[@data-testid='edit-calendar-event-submit-button']")).click();
        waitForSeconds(15);
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 2\\aftertwoevents.png");

       
        waitForSeconds(1);
        driver.findElement(By.xpath("//a[@class='fc-day-grid-event fc-h-event fc-event fc-start fc-end group_user_232880 fc-draggable' and @title='Career fair 1']")).click();
        waitForSeconds(1);
        String ExpectedEventName = driver.findElement(By.xpath("//*[@id=\"event-details-trap-focus\"]/div[1]/h2/a")).getText();
        Assert.assertEquals("Career fair 1", ExpectedEventName);
    	

    }

    	
    
    
    private void getEmailAndPassword() {
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\nike9\\eclipse-workspace\\datasheet.xlsx"));
            Workbook workbook = WorkbookFactory.create(file);

            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = 0;
            for (Row row : sheet) {
                if (rowCount == 1) {
                    int cellCount = 0;
                    for (Cell cell : row) {
                        if (cellCount == 0) {
                            email = cell.getStringCellValue();
                        } else if (cellCount == 1) {
                            password = cell.getStringCellValue();
                        }
                        cellCount++;
                    }
                    break;
                }
                rowCount++;
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login() {
    	waitForSeconds(3);
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\login\\BeforeLogin.png");
    	waitForSeconds(3);
        driver.findElement(By.id("i0116")).sendKeys(email);
        driver.findElement(By.id("idSIButton9")).click();
        waitForSeconds(3);
        driver.findElement(By.id("i0118")).sendKeys(password);
        waitForSeconds(3);
        driver.findElement(By.id("idSIButton9")).click();
        waitForSeconds(10);
        driver.findElement(By.id("trust-browser-button")).click();
        waitForSeconds(5);
        driver.findElement(By.id("idSIButton9")).click();
        waitForSeconds(3);
        takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\login\\AfterLogin.png");
        waitForSeconds(5);
    }

    private void accessTranscript() {
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 1\\hometab.png");
        driver.findElement(By.cssSelector("a[data-testid='link-resources']")).click();
        waitForSeconds(5);
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 1\\resourcestab.png");
        driver.findElement(By.id("resource-tab-Academics,_Classes_&_Registration")).click();
        waitForSeconds(5);
        String search = "transcript";
        driver.findElement(By.id("SearchBox45")).sendKeys(search);
        waitForSeconds(2);
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 1\\beforeclickingtranscriptbutton.png");
        driver.findElement(By.xpath("//*[@id='main']/div/div/div[3]/section/ul/li[1]/div/a")).click();
        waitForSeconds(2);
        switchToNextTab(driver);
        login2();
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 1\\transcripttabbeforeselection.png");
        driver.findElement(By.id("levl_id")).click();
        waitForSeconds(1);
        Actions a = new Actions(driver);
        a.keyDown(Keys.ARROW_DOWN).build().perform();
        a.keyUp(Keys.ARROW_DOWN).build().perform();
        waitForSeconds(1);
        a.keyDown(Keys.ENTER).build().perform();
        a.keyUp(Keys.ENTER).build().perform();
        waitForSeconds(1);
        driver.findElement(By.xpath("/html/body/div[3]/form/input")).click();
        String ActualTranscriptName = driver.findElement(By.xpath("/html/body/div[3]/table[1]/tbody/tr[2]/td")).getText();
        Assert.assertEquals("Nikhil kumar Bavandla", ActualTranscriptName);
    	takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 1\\transcriptdownloadstate.png");

    }

    private void printAndSavePDF(String docpath) {
        waitForSeconds(2); // Wait for the print dialog box to appear

        // Simulate pressing Ctrl + P to open the print dialog box
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("p").keyUp(Keys.CONTROL).perform();
        waitForSeconds(5); // Adjust wait time as needed

        // Simulate pressing Enter to confirm printing
        actions.sendKeys(Keys.ENTER).perform();
        waitForSeconds(10); // Wait for the PDF generation

        // Use Robot class to save the PDF
        try {
            Robot robot = new Robot();
            // Simulate pressing "Ctrl + S" to save the PDF
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            // Pause to allow the "Save As" dialog to appear
            waitForSeconds(5); // Adjust wait time as needed
            // Simulate typing the file path
            String filePath = docpath;
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection stringSelection = new StringSelection(filePath);
            clipboard.setContents(stringSelection, null);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            waitForSeconds(5);
            takeScreenshot(driver, "C:\\Users\\nike9\\eclipse-workspace\\Uni_test\\SS\\Scenario 1\\transcriptdownloadedstate.png");
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void takeScreenshot(WebDriver driver, String screenshotPath) {
        // Convert WebDriver object to TakesScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        try {
            // Capture screenshot as File
            File source = ts.getScreenshotAs(OutputType.FILE);
            // Define destination path for the screenshot
            String destinationPath = screenshotPath;
            // Copy file to the destination path
            FileUtils.copyFile(source, new File(destinationPath));
            System.out.println("Screenshot taken and saved at: " + destinationPath);
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
    
    
    public static void switchToNextTab(WebDriver driver) {
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(1));
    }

    public static void waitForSeconds(int seconds) {
        try {
            // Add a wait time specified by the parameter
            Thread.sleep(seconds * 1000); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}