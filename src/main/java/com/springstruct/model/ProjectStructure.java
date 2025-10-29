package com.springstruct.model;

import java.util.Arrays;
import java.util.List;

public class ProjectStructure {
    private final String basePackage;
    
    public ProjectStructure(String basePackage) {
        this.basePackage = basePackage;
    }
    
    public List<String> getPackages() {
        return Arrays.asList(
            "controller",
            "service", 
            "repository",
            "model",
            "dto",
            "config",
            "exception"
        );
    }
    
    public String getBasePackagePath() {
        return basePackage.replace(".", "/");
    }
    
    public String getBasePackage() {
        return basePackage;
    }
}
