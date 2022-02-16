package com.company.gherkin_file_appender.pojo.available_values_tab;

public class TariffOffering {

    private String bssId;
    private String flatOffering;
    private String availableValue;

    public TariffOffering(String bssId, String flatOffering, String availableValue) {
        this.bssId = bssId;
        this.flatOffering = flatOffering;
        this.availableValue = availableValue;
    }

    public TariffOffering() {}

    public String getFlatOffering() {
        return flatOffering;
    }

    public void setFlatOffering(String flatOffering) {
        this.flatOffering = flatOffering;
    }

    public String getBssId() {
        return bssId;
    }

    public void setBssId(String bssId) {
        this.bssId = bssId;
    }

    public String getAvailableValue() {
        return availableValue;
    }

    public void setAvailableValue(String availableValue) {
        this.availableValue = availableValue;
    }

}
