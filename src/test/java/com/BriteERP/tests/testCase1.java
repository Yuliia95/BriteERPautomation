package com.BriteERP.tests;

import com.BriteERP.pages.LoginPage;
import com.BriteERP.utilities.BrowserUtils;
import com.BriteERP.utilities.ConfigurationReader;
import com.BriteERP.utilities.Driver;
import org.testng.annotations.Test;

public class testCase1 {

    @Test
    public void Test1() {
        Driver.get().get(ConfigurationReader.get("url"));
        //1.Log in as Valid user
        LoginPage login = new LoginPage();



       // BrowserUtils.selectMenuOption(Driver.get(), "Activities", "Calendar Events");
    }
}
