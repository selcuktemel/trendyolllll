package trendyol;

import com.trendyol.selector.Selector;


import com.trendyol.selector.SelectorFactory;
import com.trendyol.selector.SelectorType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static trendyol.StepImpl.setImplicitWaitTimeout;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    protected static Selector selector;


    protected static WebDriver driver; // WebDriver tipinde statik bir değişken tanımlanıyor
    /*
   1. private: private erişim belirleyici, driver değişkeninin sadece aynı sınıf içinde erişilebilir olmasını sağlar.
    Bu, driver değişkeninin sınıfın dışındaki kodlardan doğrudan erişilmesini engeller.
    Bu şekilde, driver değişkeninin güvenli bir şekilde kontrol edilmesi ve yönetilmesi sağlanır.

   2 static: static anahtar kelimesi, driver değişkeninin sınıf düzeyinde (class-level) bir değişken olduğunu belirtir.
    Bu, tüm Driver sınıfının oluşturduğu örneklerin aynı driver değişkenini paylaşacağı anlamına gelir.
    Yani, herhangi bir Driver örneği oluşturulmasa bile, driver değişkeni hâlâ kullanılabilir.
     */

    private Driver() {
    } /*
     Java'da, bir sınıfın kurucu metodu, o sınıftan yeni bir nesne (örnek) oluşturmak için kullanılır.
     Ancak, private erişim belirleyici ile tanımlanan bir kurucu metot, yalnızca aynı sınıf içinde kullanılabilir hale getirilir.
     Bu durumda, sınıfın dışından bu kurucu metodu kullanarak yeni bir nesne oluşturulması mümkün değildir.
     Yani, private erişim belirleyici ile tanımlanan bir kurucu metot, sınıfın dışında örnekleme (instantiation) yapılmasını engeller.
     Böylece, sınıfın kontrollü bir şekilde örneklenmesi sağlanır.
     Bu tür bir kurucu metot, genellikle sınıfın tek bir örneğinin olmasını veya sınıfın statik metotları aracılığıyla örneğin alınmasını teşvik etmek için kullanılır.
     */

    public static WebDriver getDriver() {
        if (driver == null) { // driver değişkeni null ise
            String browserType = ConfigurationReader.getProperty("browser"); // "browser" isimli özelliği al
            switch (browserType) { // browserType değerine göre aşağıdaki durumlara göre işlem yap
                case "chrome": // Eğer browserType "chrome" ise
                    WebDriverManager.chromedriver().setup(); // WebDriverManager ile ChromeDriver'ı yapılandır
                    ChromeOptions options = new ChromeOptions(); // ChromeOptions sınıfından bir nesne oluştur
                    options.addArguments("--disable-notifications"); // "--disable-notifications" seçeneğini ekle
                    driver = new ChromeDriver(options); // ChromeDriver'ı bu seçeneklerle oluştur
                    driver.manage().window().maximize(); // Pencereyi maksimize et
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 10 saniye bekleme süresini ayarla
                    break;
                case "firefox": // Eğer browserType "firefox" ise
                    WebDriverManager.firefoxdriver().setup(); // WebDriverManager ile FirefoxDriver'ı yapılandır
                    driver = new FirefoxDriver(); // FirefoxDriver'ı oluştur
                    driver.manage().window().maximize(); // Pencereyi maksimize et
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 10 saniye bekleme süresini ayarla
                    break;
                default: // Geçerli bir tarayıcı türü belirtilmediyse
                    throw new IllegalArgumentException("Geçersiz tarayıcı türü: " + browserType); // Hata fırlat
            }
        }
        return driver; // Oluşturulan WebDriver nesnesini döndür
    }

    public static void closeDriver() {
        if (driver != null) { // driver değişkeni null değilse
            driver.quit(); // WebDriver'ı kapat
            driver = null; // driver değişkenini null yap
        }
    }
}



