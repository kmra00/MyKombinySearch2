import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class myKombinySearch {

    private static WebDriver driver;
    private static final String SEARCH_PHRASE = "DRAGON BALL";

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void checkMyKombiniSearch() {
        driver.get("https://mykombini.com/es/");
        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        homePage.performSearch(SEARCH_PHRASE);
        List<String> actualItems = searchResultsPage.searchResultsItemsText();
        List<String> expectedItems= searchResultsPage.searchResultItemsWithText(SEARCH_PHRASE);

        Assertions.assertEquals(expectedItems,actualItems);

    }

    @AfterAll
    public static void tearDownDriver() {
        System.out.println(LocalDateTime.now());
        driver.quit();
    }
}
