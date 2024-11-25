package tests;

import data.UserData;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HrPage;
import utilities.Driver;

import java.nio.file.Paths;
import java.util.Random;

public class RegistrationTest {
    private final HrPage hrPage = new HrPage();
    public static UserData user;

    @BeforeClass
    public void setUp(){
        hrPage.open();
    }

    public void register(UserData userData){
        hrPage.idField.sendKeys(userData.getName());
        hrPage.tcKimlikField.sendKeys(userData.getTcKimlikNo());
        hrPage.phoneField.sendKeys(userData.getPhoneNumber());
        hrPage.emailField.sendKeys(userData.getEmail());
        hrPage.birthField.sendKeys(userData.getBirthday());
    }

    @Test
    public void registrationTest(){
        user = UserData.generateUserData();
        register(user);
        String filePath = Paths.get("src","test","java","resources","deneme_CV.pdf").toAbsolutePath().toString();
        hrPage.cvField.sendKeys(filePath);

        Random rand = new Random();
        int randomIndex = rand.nextInt(hrPage.educationButtons.size());
        hrPage.educationButtons.get(randomIndex).click();
        hrPage.nextPageButton.click();

        int randomIndexes = rand.nextInt(hrPage.pozisionButtons.size());
        hrPage.pozisionButtons.get(randomIndexes).click();
        hrPage.gonderButton.click();
        Assert.assertTrue(hrPage.successMessage.isDisplayed(), "Form gönderimi başarısız!");
        System.out.println(hrPage.successMessage.getText());
    }

    @AfterClass
    public void tearDown(){
        Driver.closeDriver();
    }
}
