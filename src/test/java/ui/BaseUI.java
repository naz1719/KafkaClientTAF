package ui;

import com.project.inftrastructure.execution.logger.TestLogger;
import com.project.inftrastructure.execution.logger.UITestListener;
import com.project.inftrastructure.middlewares.ui.WebDriverManager;
import org.testng.annotations.AfterMethod;
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
    private WebDriverManager webDriverManager = WebDriverManager.getInstance();

    @Parameters("browser")
    @BeforeClass
    public void setUp(@Optional("REMOTE" /*REMOTE,CHROME,EDGE,FIREFOX*/) String browser){
        browser = getBrowserEnvVariable(browser);
        webDriverManager.setBrowserName(browser);
    }

    private String getBrowserEnvVariable(String browser) {
        String browserEnvProperty = System.getProperty("browser");
        if (browserEnvProperty != null) {
            browser = browserEnvProperty;
        }
        return browser;
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(){
        webDriverManager.tearDownDriver();
    }

    // Logger
    @BeforeMethod
    public void initLogger(Method method) {
        LOG = TestLogger.getLogger(method.getName(), method.getDeclaringClass().getSimpleName());
    }

    @AfterMethod(alwaysRun = true)
    public void dropLogger() {
        LOG.drop();
    }
}
