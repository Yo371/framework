package googlecloud;

import driver.DriverSingleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import model.GPU;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import page.CalculatorGooglePage;
import page.HomeGoogleCloudPage;
import page.ResultGooglePage;
import org.openqa.selenium.WebDriver;
import service.TestDataReader;

public class Run {
/*
    private static final String SEARCH_TEXT = TestDataReader.getTestData("search.text");

    private static final String INPUT_INSTANCES = TestDataReader.getTestData("input.instances");
    private static final String MACHINE_TYPE = TestDataReader.getTestData("machine.type");
    private static final String LOCATION = TestDataReader.getTestData("location");
    private static final String COMMITED_USAGE = TestDataReader.getTestData("commited.usage.year");


    private static final String AMOUNT_GPU = TestDataReader.getTestData("amount.gpu");
    private static final String TYPE_GPU = TestDataReader.getTestData("type.gpu");
    private static final String NODE_AMOUNT = TestDataReader.getTestData("node.amount");
    private static final String SSD = TestDataReader.getTestData("ssd");*/

    public static void main(String[] args) {

        /*System.setProperty("browser", "chrome");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = DriverSingleton.getDriver();*/

        /*HomeGoogleCloudPage homeGoogleCloudPage = new HomeGoogleCloudPage(driver);

        ResultGooglePage resultGooglePage =
        homeGoogleCloudPage.open().searchByText(SEARCH_TEXT);

        CalculatorGooglePage calculatorGooglePage =
        resultGooglePage.chooseLinkContainsSearchingText(SEARCH_TEXT);*//*
        driver.get("https://cloudpricingcalculator.appspot.com/");

        GPU gpu = new GPU(Integer.parseInt(AMOUNT_GPU), TYPE_GPU);

        CalculatorGooglePage calculatorGooglePage = new CalculatorGooglePage(driver);
        calculatorGooglePage.inputInstances(INPUT_INSTANCES)
        .selectMachineType(MACHINE_TYPE)
        .selectLocation(LOCATION)
        .selectCommittedUsage(COMMITED_USAGE)
        .addGPU(gpu)
        .selectNode(Integer.parseInt(NODE_AMOUNT))
        .addSsd(SSD);


*/

        Logger log = LogManager.getRootLogger();

        log.info("a");






    }


}
