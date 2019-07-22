import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.test.context.TestExecutionListeners;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BeispielTest {

    private WebDriver driver;

    // Hold your breath to let the site catch up. Sleep doesn't wait for the page to perform. Consider wait next time.
    public void waitForAction(final double time) {
        try {
            Thread.sleep((int) (time * 1000));
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void testumgebungEinrichten() {

        // Prepare the web driver for use in chrome. Creates a new Chrome window just once for all tests in this class.
        System.setProperty("webdriver.chrome.driver",
                "src\\main\\resources\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://localhost:8080");
    }

    @AfterClass
    public void tearDown() {
        waitForAction(10.0);
        driver.close();
    }

    @Test(groups ="a")
    public void bestCasePerfekteDaten() {

        // Startseite - Formular öffnen und mit Text befüllen
        WebElement btn = driver.findElement(By.id("startseite_toggleTextInput"));
        waitForAction(2.0);
        btn.click();

        waitForAction(2.0);
        WebElement startseite_vorname = driver.findElement(By.id("startseite_vorname"));
        startseite_vorname.sendKeys("Florian");

        WebElement startseite_nachname = driver.findElement(By.id("startseite_nachname"));
        startseite_nachname.sendKeys("Weiss");

        WebElement startseite_strasse = driver.findElement(By.id("startseite_strasse"));
        startseite_strasse.sendKeys("Musterweg");

        WebElement startseite_hausnummer = driver.findElement(By.id("startseite_hausnummer"));
        startseite_hausnummer.sendKeys("12");

        WebElement startseite_stadt = driver.findElement(By.id("startseite_stadt"));
        startseite_stadt.sendKeys("Hamburg");

        WebElement startseite_plz = driver.findElement(By.id("startseite_plz"));
        startseite_plz.sendKeys("22559");

        WebElement startseite_geburtsdatum = driver.findElement(By.id("startseite_geburtsdatum"));
        startseite_geburtsdatum.sendKeys("12.12.1212");

        WebElement startseite_geburtsort = driver.findElement(By.id("startseite_geburtsort"));
        startseite_geburtsort.sendKeys("Hamburg");

        WebElement startseite_staatsangehoerigkeit = driver.findElement(By.id("startseite_staatsangehoerigkeit"));
        startseite_staatsangehoerigkeit.sendKeys("DE");

        WebElement startseite_ausweisID = driver.findElement(By.id("startseite_ausweisID"));
        startseite_ausweisID.sendKeys("D123456789");

        WebElement startseite_echtheitsMerkmal = driver.findElement(By.id("startseite_echtheitsMerkmal"));
        startseite_echtheitsMerkmal.sendKeys("Personalausweis"); // Notwendig für korrekte Validierung

        // Scrollt die Seite runter
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,100)");

        WebElement btnSubmit = driver.findElement(By.id("startseite_submit"));
        waitForAction(2.0);
        btnSubmit.click();

        // Pruefungsseite - Eingaben positiv bestätigen

        WebElement btnBestaetigen = driver.findElement(By.id("pruefung_datenBestaetigen"));
        waitForAction(2.0);
        btnBestaetigen.click();
        waitForAction(2.0);
    }

    @Test(groups = "b", dependsOnGroups = "a")
    public void CaseDatenBearbeiten() {
        driver.get("http://localhost:8080");

        // Startseite - Formular öffnen und mit Text befüllen
        WebElement btn = driver.findElement(By.id("startseite_toggleTextInput"));
        waitForAction(2.0);
        btn.click();

        waitForAction(2.0);
        WebElement startseite_vorname = driver.findElement(By.id("startseite_vorname"));
        startseite_vorname.sendKeys("Florian");

        WebElement startseite_nachname = driver.findElement(By.id("startseite_nachname"));
        startseite_nachname.sendKeys("Weiss");

        WebElement startseite_strasse = driver.findElement(By.id("startseite_strasse"));
        startseite_strasse.sendKeys("Musterweg");

        WebElement startseite_hausnummer = driver.findElement(By.id("startseite_hausnummer"));
        startseite_hausnummer.sendKeys("12");

        WebElement startseite_stadt = driver.findElement(By.id("startseite_stadt"));
        startseite_stadt.sendKeys("Hamburg");

        WebElement startseite_plz = driver.findElement(By.id("startseite_plz"));
        startseite_plz.sendKeys("22559");

        WebElement startseite_geburtsdatum = driver.findElement(By.id("startseite_geburtsdatum"));
        startseite_geburtsdatum.sendKeys("12.12.1212");

        WebElement startseite_geburtsort = driver.findElement(By.id("startseite_geburtsort"));
        startseite_geburtsort.sendKeys("Hamburg");

        WebElement startseite_staatsangehoerigkeit = driver.findElement(By.id("startseite_staatsangehoerigkeit"));
        startseite_staatsangehoerigkeit.sendKeys("DE");

        WebElement startseite_ausweisID = driver.findElement(By.id("startseite_ausweisID"));
        startseite_ausweisID.sendKeys("D123456789");

        WebElement startseite_echtheitsMerkmal = driver.findElement(By.id("startseite_echtheitsMerkmal"));
        startseite_echtheitsMerkmal.sendKeys("Personalausweis"); // Notwendig für korrekte Validierung

        // Scrollt die Seite runter
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,100)");

        WebElement btnSubmit = driver.findElement(By.id("startseite_submit"));
        waitForAction(2.0);
        btnSubmit.click();

        // Pruefungsseite - Eingaben bearbeiten

        WebElement btnDatenBearbeiten = driver.findElement(By.id("pruefung_datenBearbeiten"));
        waitForAction(2.0);
        btnDatenBearbeiten.click();

//Bearbeitungsseite - Eingaben abändern und bestätigen

        waitForAction(2.0);
        WebElement bearbeitung_vorname = driver.findElement(By.id("bearbeitung_vorname"));
        bearbeitung_vorname.clear();
        bearbeitung_vorname.sendKeys("Viet");

        WebElement bearbeitung_nachname = driver.findElement(By.id("bearbeitung_nachname"));
        bearbeitung_nachname.clear();
        bearbeitung_nachname.sendKeys("Ng");

        WebElement btnDatenBearbeitenBestaetigen = driver.findElement(By.id("bearbeitung_bestaetigen"));
        waitForAction(2.0);
        btnDatenBearbeitenBestaetigen.click();

        // Pruefungsseite - Eingaben positiv bestätigen


        WebElement btnBestaetigen = driver.findElement(By.id("pruefung_datenBestaetigen"));
        waitForAction(2.0);
        btnBestaetigen.click();
        waitForAction(2.0);
    }

    @Test(groups = "c", dependsOnGroups = "b")
    public void CaseKeineUebereinstimmungMitBundesDB() {
        driver.get("http://localhost:8080");

        // Startseite - Formular öffnen und mit Text befüllen
        WebElement btn = driver.findElement(By.id("startseite_toggleTextInput"));
        waitForAction(2.0);
        btn.click();

        waitForAction(2.0);
        WebElement startseite_vorname = driver.findElement(By.id("startseite_vorname"));
        startseite_vorname.sendKeys("Florian");

        WebElement startseite_nachname = driver.findElement(By.id("startseite_nachname"));
        startseite_nachname.sendKeys("Mueller");

        WebElement startseite_strasse = driver.findElement(By.id("startseite_strasse"));
        startseite_strasse.sendKeys("Musterweg");

        WebElement startseite_hausnummer = driver.findElement(By.id("startseite_hausnummer"));
        startseite_hausnummer.sendKeys("12");

        WebElement startseite_stadt = driver.findElement(By.id("startseite_stadt"));
        startseite_stadt.sendKeys("Hamburg");

        WebElement startseite_plz = driver.findElement(By.id("startseite_plz"));
        startseite_plz.sendKeys("22559");

        WebElement startseite_geburtsdatum = driver.findElement(By.id("startseite_geburtsdatum"));
        startseite_geburtsdatum.sendKeys("12.12.1212");

        WebElement startseite_geburtsort = driver.findElement(By.id("startseite_geburtsort"));
        startseite_geburtsort.sendKeys("Hamburg");

        WebElement startseite_staatsangehoerigkeit = driver.findElement(By.id("startseite_staatsangehoerigkeit"));
        startseite_staatsangehoerigkeit.sendKeys("DE");

        WebElement startseite_ausweisID = driver.findElement(By.id("startseite_ausweisID"));
        startseite_ausweisID.sendKeys("D123456789");

        WebElement startseite_echtheitsMerkmal = driver.findElement(By.id("startseite_echtheitsMerkmal"));
        startseite_echtheitsMerkmal.sendKeys("Personalausweis"); // Notwendig für korrekte Validierung

        // Scrollt die Seite runter
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,100)");

        WebElement btnSubmit = driver.findElement(By.id("startseite_submit"));
        waitForAction(2.0);
        btnSubmit.click();

        // Pruefungsseite - Eingaben positiv bestätigen

        WebElement btnBestaetigen = driver.findElement(By.id("pruefung_datenBestaetigen"));
        waitForAction(2.0);
        btnBestaetigen.click();
        waitForAction(2.0);
    }

}
