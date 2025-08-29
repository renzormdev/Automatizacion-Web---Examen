package com.nttdata.steps;

import com.nttdata.page.LoginPage;

public class LoginSteps {

    private LoginPage loginPage;

    public LoginSteps(){
        this.loginPage = new LoginPage();
    }

    public void login(String user, String password){
        loginPage.openStore();
        loginPage.typeUser(user);
        loginPage.typePassword(password);
        loginPage.clickLogin();
    }
}
