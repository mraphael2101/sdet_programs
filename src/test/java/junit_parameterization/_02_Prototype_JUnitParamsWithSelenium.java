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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class _02_Prototype_JUnitParamsWithSelenium {
    private WebDriver driver;
    @Parameter(value=0)
    public int inputA;
    @Parameter(value=1)
    public int inputB;

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
        driver.quit();
    }

    @Parameters(name = "{index}: firstTestScenario({0}{1}")
    public static Collection<Object[]> sampleData() {
        return Arrays.asList(new Object[][] {
                {0, 1}, {1, 1}, {2, 2}
        });
    }

    @Test
    public void firstTestScenario() {
        new _01_FileReader().readFile(System.getProperty("user.dir")
                + "\\src\\com\\company\\csv_reader\\SampleData.csv");
        seleniumMethod1();
        seleniumMethod2();
        seleniumMethod3();
    }

    private void seleniumMethod1() {
        driver.get("http://www.google.com");

    }

    private void seleniumMethod2() {
        System.out.println(driver.getTitle());
    }

    private void seleniumMethod3() {
        assertEquals(inputA, inputB);
    }
}