package com.mijazz.springlearn.Configurator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesPathConfigurator {
    @Value("${pathconfigurator.CFilePath}")
    private String cfilepath;

    @Value("${pathconfigurator.HWFilePath}")
    private String hwfilepath;

    public String getCfilepath() {
        return cfilepath;
    }

    public void setCfilepath(String cfilepath) {
        this.cfilepath = cfilepath;
    }

    public String getHwfilepath() {
        return hwfilepath;
    }

    public void setHwfilepath(String hwfilepath) {
        this.hwfilepath = hwfilepath;
    }
}
