package hellocucumber.stepDefinitions.hooks;

import hellocucumber.utils.DriverFactory;
import hellocucumber.utils.ReadProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import hellocucumber.utils.context.ScenarioContextInfoHolder;

import java.time.LocalTime;

import static hellocucumber.utils.UtilMethods.chromeOptionsConfig;


public class init {
	DriverFactory driverFactory;
	ScenarioContextInfoHolder context;

	private RequestSpecification requestSpecification;

	public init(ScenarioContextInfoHolder context, DriverFactory driverFactory) {
		this.context = context;
		this.driverFactory = driverFactory;
	}

	@Before
	public void initializeTest(Scenario scenario) {
		String local = "LOCAL";
//		if (getEnvironmentVariable(PLATFORM_GOAL_EXECUTION_TEST).equalsIgnoreCase(local)) {
		if ("LOCAL".equalsIgnoreCase(local)) {
			this.driverFactory.setDriver(new ChromeDriver(chromeOptionsConfig()));
			System.out.println("Starting the session id " + this.driverFactory.getDriver().getSessionId()
							+ " related to scenario called : " + scenario.getName()
							+ " and environment : " + LocalTime.now());
		}
		this.driverFactory.getDriver().manage().window().maximize();
		this.driverFactory.getDriver().get(ReadProperties.getInstance().getProperty("LOGIN_URL"));
		this.driverFactory.setRequestFactory(this.driverFactory.getRequestFactory());
		this.driverFactory.InitializePageObject(this.driverFactory.getDriver(), scenario);
	}

	@After
	public void TearDownTest(Scenario sc) {
		if (this.driverFactory.getDriver() != null) {
			System.out.println("Closing the session id " + this.driverFactory.getDriver().getSessionId() + " related to the scenario called : " + sc.getName() + " and finished : " + LocalTime.now());
			System.out.println("The source tag names invoked in the scenario are : " + sc.getSourceTagNames().toString());
			this.driverFactory.getDriver().quit();
		} else {
			System.out.println("Scenario name: " + sc.getName());
			System.out.println("The source tag names invoked in the scenario are : " + sc.getSourceTagNames().toString());
		}
	}
}