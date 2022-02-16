package com.company.gherkin_file_appender.unit_tests.utility;

import com.company.gherkin_file_appender.pojo.available_values_tab.TariffOffering;
import com.company.gherkin_file_appender.unit_tests.config.TestBase_Mocked;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class MockitoExample extends TestBase_Mocked {
    private TariffOffering tariffOffering1;
    private TariffOffering tariffOffering2;
    private List<TariffOffering> tariffOfferingList;

    @Test
    public void mockitoKeyFeaturesExample() {
        // mock the value returned when calling a specific method
        when(utility.getPartialOutputFilePath()).thenReturn("SampleTestName");

        // verify that the method is called once
        //verify(DemoImpl, times(1)).setTestMethodName(testInfo);
    }
}
