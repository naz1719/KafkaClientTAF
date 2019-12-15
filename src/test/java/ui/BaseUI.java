package ui;

import com.project.inftrastructure.execution.logger.TestLogger;
import com.project.inftrastructure.execution.logger.UITestListener;
import com.project.inftrastructure.execution.wait.UIWaitManager;
import com.project.inftrastructure.middlewares.ui.UiConfiguration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

/**
 * Base Test class for UI tests. Contains preconditions.
 */
@Listeners(UITestListener.class)
public abstract class BaseUI {

    protected TestLogger LOG;
    protected WebDriver driver;
    protected UIWaitManager waitManager;
    protected UiConfiguration uiConfiguration = UiConfiguration.getInstance();

    @Parameters("browser")
    @BeforeClass
    public void setUp(@Optional("CHROME"/*CHROME,EDGE,FIREFOX*/) String browser){
        uiConfiguration.setBrowserName(browser);
        driver = uiConfiguration.getDriver();
    }

    @AfterSuite
    public void closeDriver(){
        driver.quit();
        driver = null;
    }

    // Logger
    @BeforeMethod
    public void initLogger(Method method) {
        LOG = TestLogger.getLogger(method.getName(), method.getDeclaringClass().getSimpleName());
        waitManager = UIWaitManager.getInstance();
    }
    @AfterMethod
    public void dropLogger() {
        LOG.drop();
    }
}
