package com.BriteERP.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class NavigationBoard {
    public void selectModule(WebDriver driver, String module){
        driver.findElement(By.xpath("//span[@class='oe_menu_text' and contains(text(),'"+module+"')]")).click();
    }
}
