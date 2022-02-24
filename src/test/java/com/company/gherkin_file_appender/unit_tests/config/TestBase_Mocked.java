package com.company.gherkin_file_appender.unit_tests.config;

import com.company.gherkin_file_appender.config.Prototype_AppendDataToFeatureFileUtility;
import com.company.gherkin_file_appender.pojo.first_tab.PriceInformation;
import com.company.gherkin_file_appender.pojo.first_tab.TariffOffering;
import org.junit.BeforeClass;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class TestBase_Mocked {

    @InjectMocks
    protected Prototype_AppendDataToFeatureFileUtility utility;

    @Mock
    protected PriceInformation priceInfo;

    @Mock
    protected TariffOffering tariffOffering1, tariffOffering2;

    protected List<TariffOffering> tariffOfferingList;

    @BeforeClass
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);

        tariffOffering1.setBssId("abc");
        tariffOffering1.setFlatOffering("def");
        tariffOffering1.setAvailableValue("fgh");
        priceInfo.setPrice(10.00d);
        tariffOffering1.setPriceInformation(priceInfo);

        tariffOffering2.setBssId("123");
        tariffOffering2.setFlatOffering("456");
        tariffOffering2.setAvailableValue("789");
        priceInfo.setPrice(5.00d);
        tariffOffering2.setPriceInformation(priceInfo);

        tariffOfferingList.add(tariffOffering1);
        tariffOfferingList.add(tariffOffering2);
    }
}
