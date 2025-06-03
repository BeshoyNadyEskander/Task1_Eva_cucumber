package org.example.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(


        features = "src/main/resources/Features",
        glue = "org.example.stepDefs",
        tags = "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
        plugin = { "pretty",
                "html:target/cucumber.html",
                "json:target/cucumber.json",
                "junit:target/cukes.xml",
                "rerun:target/rerun.txt"

        }

)

public class AllureRunner extends AbstractTestNGCucumberTests {
}
