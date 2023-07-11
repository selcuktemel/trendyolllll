package trendyol;

import com.github.javafaker.Faker;

import com.trendyol.model.SelectorInfo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static trendyol.Driver.driver;
import static trendyol.Driver.selector;

public class StepImpl  {
    public StepImpl() throws IOException {
        PageFactory.initElements(Driver.getDriver(), this);

    }



    private Logger logger = LoggerFactory.getLogger(getClass());

    Actions actions = new Actions(driver);



    Wait<WebDriver> wait = new FluentWait<>(driver);





    public List<WebElement> findElements(By by) throws Exception {
        List<WebElement> webElementList = null;

        try {
            webElementList = (List<WebElement>) wait.until(new ExpectedCondition<List<WebElement>>() {
                @Nullable
                @Override
                public List<WebElement> apply(@Nullable WebDriver driver) {
                    List<WebElement> elements = driver.findElements(by);
                    return elements.size() > 0 ? elements : null;
                }
            });
            if (webElementList == null) {
                throw new NullPointerException(String.format("by = %s Web element list not found", by.toString()));
            }
        } catch (Exception e) {
            throw e;
        }
        return webElementList;
    }


    public List<WebElement> findElementsWithoutAssert(By by) {

        List<WebElement> mobileElements = null;
        try {
            mobileElements = findElements(by);
        } catch (Exception e) {

        }
        return mobileElements;
    }

    public List<WebElement> findElementsWithAssert(By by) {

        List<WebElement> mobileElements = null;
        try {
            mobileElements = findElements(by);
        } catch (Exception e) {
            Assert.fail("by = %s Elements not found ");
            e.printStackTrace();
        }
        return mobileElements;
    }


    public WebElement findElement(By by) throws Exception {
        WebElement webElement;
        try {
            webElement = findElements(by).get(0);
        } catch (Exception e) {
            throw e;
        }
        return webElement;
    }

    public WebElement findElementWithoutAssert(By by) {
        WebElement mobileElement = null;
        try {
            mobileElement = findElement(by);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobileElement;
    }

    public WebElement findElementWithAssertion(By by) {
        WebElement webElement = null;
        try {
            webElement = findElement(by);
        } catch (Exception e) {
            Assert.fail(webElement.getAttribute("value") + " " + "by = %s Element not found ");
            e.printStackTrace();
        }
        return webElement;
    }

    public WebElement findElementByKeyWithoutAssert(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        WebElement webElement = null;
        try {
            webElement = selectorInfo.getIndex() > 0 ? findElements(selectorInfo.getBy())
                    .get(selectorInfo.getIndex()) : findElement(selectorInfo.getBy());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webElement;
    }

    public WebElement findElementByKey(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);

        WebElement mobileElement = null;
        try {
            mobileElement = selectorInfo.getIndex() > 0 ? findElements(selectorInfo.getBy())
                    .get(selectorInfo.getIndex()) : findElement(selectorInfo.getBy());
        } catch (Exception e) {
            Assert.fail("key = %s by = %s Element not found ");
            e.printStackTrace();
        }
        return mobileElement;
    }


    public List<WebElement> findElemenstByKeyWithoutAssert(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        List<WebElement> webElementList = null;
        try {
            webElementList = findElements(selectorInfo.getBy());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webElementList;
    }

    public List<WebElement> findElemenstByKey(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        List<WebElement> webElementList = null;
        try {
            webElementList = findElements(selectorInfo.getBy());
        } catch (Exception e) {
            Assert.fail("key = %s by = %s Elements not found ");
            e.printStackTrace();
        }
        return webElementList;
    }


    private int getScreenWidth() {
        return driver.manage().window().getSize().width;
    }

    private int getScreenHeight() {
        return driver.manage().window().getSize().height;
    }

    private int getScreenWithRateToPercent(int percent) {
        return getScreenWidth() * percent / 100;
    }

    private int getScreenHeightRateToPercent(int percent) {
        return getScreenHeight() * percent / 100;
    }


    public boolean isElementPresent(By by) {
        return findElementWithoutAssert(by) != null;
    }


    private void backPage() {
        driver.navigate().back();
    }

    public boolean doesElementExistByKey(String key, int time) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        try {
            WebDriverWait elementExist = new WebDriverWait(driver, time);
            elementExist.until(ExpectedConditions.visibilityOfElementLocated(selectorInfo.getBy()));
            return true;
        } catch (Exception e) {
            logger.info(key + " aranan elementi bulamadı");
            return false;
        }

    }


    @Given("Kullanici trendyol anasayfasinda")
    public void kullanciTrendyolAnasayfasında() {
        driver.get(ConfigurationReader.getProperty("url"));
    }

    // @When("{2} saniye bekle")
    public static int setImplicitWaitTimeout(int timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
        return timeoutInSeconds;
    }

    @When("<key> li elemente tikla <text> gonder")
    public void gonderElementWithKey2(String key, String text) {


        actions.click(findElementByKey(key)).perform();
        actions.moveToElement(findElementByKey(key)).sendKeys(text).perform();

    }

    @When("\"{key}\" li elemente tikla")
    public void elementeTikla(String key){

      doesElementExistByKey(key, 2);

      actions.click(findElementByKey(key)).perform();
    }


    @When("<key> li elemente <text> gonder")
    public void gonderKey2(String key, String text) {

        actions.moveToElement(findElementByKey(key)).sendKeys(text).perform();
    }


    @When("Hesapta kullanici varsa cikis yap")
    public void tapElementWithKeyLoginControlArea() {
        logger.info("element varsa verilen tıkla ya girdi");
        boolean hasLoggedIn = doesElementExistByKey("girisYapUyeOlButonu", 2);
        if (!hasLoggedIn) {
            findElementByKey("kullanciDegistir").click();
            logger.info("kullanciDegistir" + " elementine tiklanir");
            findElementByKey("girisYapUyeOlButonu").click();
            logger.info("girisYapUyeOlButonu" + " elementine tiklanir");

        }
    }


    @When("<key> li  element varsa <text> giris yap yoksa diger adima gec")
    public void otpVarsaGirisYap(String key, String text) {

        boolean elementVarsa = doesElementExistByKey(key, 2);
        if (elementVarsa) {
            actions.moveToElement(findElementByKey(key)).sendKeys(text).perform();
            logger.info(key + " elementine giris yapildi");
        } else {
            logger.info(key + "  alani bulunamadi. Diger adima geçiliyor...");

        }
    }

    @When("<key> elementi varsa tikla yoksa diger adima gec")
    public void elementVarsaTiklaYoksaDevamEt(String key) {
        logger.info("element varsa tikla");
        boolean button = doesElementExistByKey(key, 2);
        if (button) {
            findElementByKey(key).click();
            logger.info(key + " elementine giris yapildi");
        } else {
            logger.info(key + "  alani bulunamadi. Diger adima geciliyor.");

        }
    }

    @When("<key> elementi dönüştür <key2> alanına gönder")
    public void elementiDonustur(String key, String key2) {
        logger.info("Elementi Dönüştür");
        doesElementExistByKey(key, 2);

        String metin = findElementByKey(key).getText();
        double odemeTutari = Double.parseDouble(extractOdemeTutari(metin));
        logger.info(key + " elementi " + odemeTutari + " stringine dönüştürüldü");
        System.out.println(odemeTutari);
        actions.sendKeys(findElementByKey(key2), String.valueOf(odemeTutari));
        actions.sendKeys(Keys.ENTER);
        actions.perform();
    }

    public static void main(String[] args) {

    }

    public static String extractOdemeTutari(String metin) {
        String[] parts = metin.split(": ");

        if (parts.length > 1) {
            String tutar = parts[1];
            tutar = tutar.replaceAll("[^\\d,.]", "");
            tutar = tutar.replace(',', '.'); // Virgülü noktaya çevir
            return tutar;
        }
        return null;
    }


    Faker faker = new Faker();


    @When("fakerEmail textini <alana> elemente yaz")
    public void fakerEmailYazma(String alana) {
        doesElementExistByKey(alana, 5);
        findElementByKey(alana).sendKeys(faker.internet().emailAddress());
    }

    @When("fakerPhoneNumber textini <alana> elemente yaz")
    public void fakerTelefonNumarsiYazma(String alana) {
        doesElementExistByKey(alana, 5);

        actions.sendKeys(findElementByKey(alana), ("0(531" + faker.number().digits(7))).perform();

    }


    @When("excelden ilk satiri silme")
    public void ilkSatiriSilme() {
        logger.info("excele girildi");

        String filePath = "src/test/resources/Kart/kart.xlsx";
        int rowToDelete = 0; // İlk satırı silmek için indeks olarak 0 kullanılır

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // İlk çalışma sayfasını seçin
            Row row = sheet.getRow(rowToDelete);
            if (row != null) {
                sheet.removeRow(row); // Satırı silin
                sheet.shiftRows(rowToDelete + 1, sheet.getLastRowNum(), -1); // Sıradaki satırları yukarı kaydırın
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos); // Güncellenmiş Excel dosyasını kaydedin
            }

            logger.info("kart listeden silindi...");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

