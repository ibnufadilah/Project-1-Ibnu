package login;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Case1 {
    @Test
    @DisplayName("Positif Login")
    public void positif_login (){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://practicetestautomation.com/practice-test-login/");
        page.locator("//input[@id='username']").fill("student");
        page.locator("//input[@id='password']").fill("Password123");
        page.locator("//button[@id='submit']").click();
        page.waitForURL("https://practicetestautomation.com/logged-in-successfully/");
        //validasi URL yang masuk benar
        String currentUrl = page.url();
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        if (currentUrl.equals(expectedUrl)) {
            System.out.println("URL is correct: " + currentUrl);
        } else {
            System.out.println("URL is incorrect. Expected: " + expectedUrl + ", but got: " + currentUrl);
        }
         //System.out.println(currentUrl);

        //validasi body text ketika berhasil login
        ElementHandle successMessage = page.waitForSelector("body >> text=Logged In Successfully");
        if (successMessage != null) {
            System.out.println("--Login berhasil! Teks 'Logged In Successfully' ditemukan.--");
        } else {
            System.out.println("--Gagal login atau teks 'Logged In Successfully' tidak ditemukan.--");
        }

        //validasi ada button logout
        ElementHandle logoutButton = page.waitForSelector("//a[contains(text(),'Log out')]");
        if (logoutButton != null){
            System.out.println("--Ada button logoutnya 'Berarti Login Berhasil.'--");
        } else {
            System.out.println("--login gagal button logout tidak ditemukan--");
        }



        page.close();
        browser.close();
        playwright.close();

    }

    @Test
    @DisplayName("Negatif Login Wrong Username")
    public void wrongusername(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://practicetestautomation.com/practice-test-login/");
        page.locator("//input[@id='username']").fill("incorrectUser");
        page.locator("//input[@id='password']").fill("Password123");
        page.locator("//button[@id='submit']").click();

        ElementHandle usernameinvalid = page.waitForSelector("//div[@id='error']");
        if (usernameinvalid != null){
            System.out.println("--Username salah--");
        } else {
            System.out.println(("--Berhasil login--"));
        }
        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Negatif Login Wrong Password")
    public void wrongpassword (){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://practicetestautomation.com/practice-test-login/");
        page.locator("//input[@id='username']").fill("student");
        page.locator("//input[@id='password']").fill("incorrectPassword");
        page.locator("//button[@id='submit']").click();

        ElementHandle usernameinvalid = page.waitForSelector("//div[@id='error']");
        if (usernameinvalid != null){
            System.out.println("--Password salah--");
        } else {
            System.out.println(("--Berhasil login--"));
        }
        page.close();
        browser.close();
        playwright.close();
    }
}
