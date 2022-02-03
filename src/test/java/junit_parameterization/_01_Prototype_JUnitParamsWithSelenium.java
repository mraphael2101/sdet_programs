package junit_parameterization;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class _01_Prototype_JUnitParamsWithSelenium {
    WebDriver driver;

    public _01_Prototype_JUnitParamsWithSelenium() {
        driver = new ChromeDriver();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> sampleData() {
        return Arrays.asList(new Object[][] {
                {0,1},{1,1},{2,2}
        });
    }

    @Test
    public void parameterisedTest(int input1, int input2) {
            assertEquals(input1, input2);
    }

    private void seleniumMethod1() {

    }


    private void seleniumMethod2() {

    }


    private void seleniumMethod3() {

    }
}
