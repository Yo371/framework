package test;

import driver.DriverSingleton;
import model.GPU;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import page.*;
import service.TestDataReader;
import utils.TestListener;

@Listeners(TestListener.class)
public class CommonConditionTest {

    // mvn -Dbrowser=chrome -DsuiteXmlFile=src\test\resources\testng-smoke.xml clean test
// mvn -Dbrowser=chrome -Denvironment=calculator -Dsurefire.suiteXmlFiles=src\test\resources\testng-smoke.xml clean test
    static {
        System.setProperty("environment", "calculator");
        System.setProperty("browser", "chrome");
    }

    protected WebDriver driver;
    protected CalculatorGooglePage calculatorGooglePage;
    protected static final String SEARCH_TEXT = TestDataReader.getTestData("search.text");

    protected static final String INPUT_INSTANCES = TestDataReader.getTestData("input.instances");
    protected static final String MACHINE_TYPE = TestDataReader.getTestData("machine.type");
    protected static final String LOCATION = TestDataReader.getTestData("location");
    protected static final String COMMITED_USAGE = TestDataReader.getTestData("commited.usage.year");

    protected static final String AMOUNT_GPU = TestDataReader.getTestData("amount.gpu");
    protected static final String TYPE_GPU = TestDataReader.getTestData("type.gpu");
    protected static final String NODE_AMOUNT = TestDataReader.getTestData("node.amount");
    protected static final String SSD = TestDataReader.getTestData("ssd");
    protected static final String READY_COST_MONTH = TestDataReader.getTestData("ready.cost.month");



    @BeforeMethod()
    public void setUp() {

        driver = DriverSingleton.getDriver();
        addOptionsToCalculatorAndEstimateAll();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

    protected void addOptionsToCalculatorAndEstimateAll() {
        ResultGooglePage resultGooglePage =
                new HomeGoogleCloudPage(driver).open().searchByText(SEARCH_TEXT);

        calculatorGooglePage =
                resultGooglePage.chooseLinkContainsSearchingText(SEARCH_TEXT);

        calculatorGooglePage.switchFrame();

        InstancesAreaCalculatorPage calculatorInstancesAreaPage = calculatorGooglePage.getInstancesAreaPage();
        calculatorInstancesAreaPage.inputInstances(INPUT_INSTANCES)
                .selectMachineType(MACHINE_TYPE)
                .selectLocation(LOCATION)
                .selectCommittedUsage(COMMITED_USAGE);

        NodesAreaCalculatorPage calculatorNodesAreaPage = calculatorGooglePage.getNodesAreaPage();

        GPU gpu = new GPU(Integer.parseInt(AMOUNT_GPU), TYPE_GPU);

        calculatorNodesAreaPage.addGPU(gpu).addSsd(SSD).selectNode(Integer.parseInt(NODE_AMOUNT));

        calculatorGooglePage.estimateInstancesField().estimateNodesField();

    }

}
