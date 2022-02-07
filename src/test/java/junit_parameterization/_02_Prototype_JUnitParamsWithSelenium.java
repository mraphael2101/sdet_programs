package junit_parameterization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class _02_Prototype_JUnitParamsWithSelenium {
    @Parameter(value=0) public String inputA;
    @Parameter(value=1) public String inputB;
    //@Parameter(value=2) public String inputC;
    //@Parameter(value=3) public String inputD;
    private WebDriver driver;
    private static final int FIELD_1_INDEX = 0;
    private static String[] tokens;
    private static List<String> datasetAsList = new ArrayList<>();

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void beforeTest() {
        driver = RemoteWebDriver.builder().oneOf(new ChromeOptions()).build();
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void afterTest() {
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        driver.quit();
    }

    @Parameters(name = "{index}: firstTestScenario({0}{1}")
    public static Collection<Object[]> sampleData() {
        BufferedReader fileReader = null;   // Ignores the header record (first line)
        String line = "";
        try {
            fileReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\SampleData.csv"));
            fileReader.readLine();

            while ((line = fileReader.readLine()) != null) {
                tokens = line.split(",");
                if(tokens.length > 0) {
                    if(tokens[FIELD_1_INDEX].contains("a\'")) {
                        // do something e.g. cleansing happens here
                        tokens[FIELD_1_INDEX].replaceAll("a\'","");
                    }
                    // Assign the value of each excel field to an attribute to manipulate
                    // String field1 = tokens[FIELD_1_INDEX];

                }
                for (String token: tokens) {
                    datasetAsList.add(token);
                }
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Arrays.asList(new Object[][] {
                { datasetAsList.get(0), datasetAsList.get(1)},
                { datasetAsList.get(2), datasetAsList.get(3)},
                { datasetAsList.get(4), datasetAsList.get(5)},
                { datasetAsList.get(6), datasetAsList.get(7)}
        });
    }

    @Test
    public void firstTestScenario() {
        seleniumMethod1();
        seleniumMethod2();
        seleniumMethod3();
    }

    private void seleniumMethod1() {
        driver.get("http://www.google.com");
        driver.findElement(By.id("L2AGLb")).click();
    }

    private void seleniumMethod2() {
        driver.findElement(By.tagName("input")).sendKeys(inputA + " " +  inputB);
    }

    private void seleniumMethod3() {
        driver.findElement(By.xpath(" //div[@class='FPdoLc lJ9FBc']//input[@name='btnK']")).click();
    }
}