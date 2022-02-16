package com.company.gherkin_file_appender.unit_tests.config;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import com.company.gherkin_file_appender.pojo.available_values_tab.PriceInformation;
import com.company.gherkin_file_appender.pojo.available_values_tab.TariffOffering;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestBase_Mocked {

    @InjectMocks
    protected Prototype_AppendDataToFeatureFileUtility utility;

    @Mock
    PriceInformation priceInfo = new PriceInformation();

    @Mock
    TariffOffering tariffOffering = new TariffOffering();

    @Before
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
    }
}
