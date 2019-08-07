package com.BriteERP.pages;

import com.BriteERP.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CRMPage extends NavigationBoard {
    public CRMPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "[data-menu-xmlid='crm.crm_menu_root']")
    public WebElement cRMModule;
    @FindBy(css = "[aria-label='list']")
    public WebElement listView;
    @FindBy(css = "[aria-label='pivot']")
    public WebElement pivotView;
    @FindBy(xpath = "//table/tbody//tr/td[2]")
    public WebElement totalRevenuePivot;

    public void clickOnOpportunityPivot() {
        Driver.get().findElement(By.xpath("//td[@class='o_pivot_header_cell_closed']")).click();
        Driver.get().findElement(By.xpath("//a[.='Opportunity']")).click();
    }

    public String[] getRevenueByRow(int row) {
        String title = Driver.get().findElement(By.xpath("//table[@class='table-hover table-condensed table-bordered']/tbody/tr[" + (row + 1) + "]/td[1]")).getText();
        String expectedRevenue = Driver.get().findElement(By.xpath("//table[@class='table-hover table-condensed table-bordered']/tbody/tr[" + (row + 1) + "]/td[2]")).getText();
        return new String[]{title, expectedRevenue};
    }

    public int getColumnOfHeaderList(String header) {
        List<WebElement> listOfHeaders = Driver.get().findElements(By.xpath("//table[@class='o_list_view table table-condensed table-striped o_list_view_ungrouped']/thead//th"));
        //Finding the index of Expected Revenue column:
        for (int i = 0; i < listOfHeaders.size(); i++) {
            if (listOfHeaders.get(i).getText().equals(header)) {
                return i + 1;
            }
        }
        return -1;
    }

    public String getCellByTitle(String title, String header) {
        return Driver.get().findElement(By.xpath("//table[@class='o_list_view table table-condensed table-striped o_list_view_ungrouped']//*[.='" + title + "']/../td[" + getColumnOfHeaderList(header) + "]")).getText();
    }

    public int numberOfRowslist() {
        return Driver.get().findElements(By.xpath("//table[@class='o_list_view table table-condensed table-striped o_list_view_ungrouped']//tbody/tr")).size();
    }

    public List<WebElement> listOfElementsInColumn(String header) {
        return Driver.get().findElements(By.xpath("//table[@class='o_list_view table table-condensed table-striped o_list_view_ungrouped']//tbody/tr/td[" + getColumnOfHeaderList(header) + "]"));
    }
}