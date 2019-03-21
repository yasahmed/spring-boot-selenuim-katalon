package com.selenuim.demo.klov;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class test extends baseTest {


    @Test
    public void login_OK() throws Exception {
        driver.get("https://www.rainworx.com/AWDemo31");
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("Admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("demo");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::input[4]")).click();
        driver.findElement(By.id("FullTextQuery1")).click();
        driver.findElement(By.id("FullTextQuery1")).click();
        // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=FullTextQuery1 | ]]
        driver.findElement(By.id("FullTextQuery1")).clear();
        driver.findElement(By.id("FullTextQuery1")).sendKeys("house");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Advanced Search'])[2]/following::button[1]")).click();
    }

    @Test
    public void searchHouse_OK() throws Exception {
        driver.get("https://www.rainworx.com/AWDemo31/Browse");
        driver.findElement(By.id("FullTextQuery1")).click();
        driver.findElement(By.id("FullTextQuery1")).clear();
        driver.findElement(By.id("FullTextQuery1")).sendKeys("house");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Advanced Search'])[2]/following::button[1]")).click();
    }

    @Test
    public void searchHouse_KO() throws Exception {
        driver.get("https://www.rainworx.com/AWDemo31/Browse");
        driver.findElement(By.id("FullTextQuery1")).click();
        driver.findElement(By.id("FullTextQuery1")).clear();
        driver.findElement(By.id("FullTextQuery111")).sendKeys("house");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Advanced Search'])[2]/following::button[1]")).click();
    }
}
