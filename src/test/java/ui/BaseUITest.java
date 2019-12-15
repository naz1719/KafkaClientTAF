package ui;

import com.project.inftrastructure.execution.logger.TestLogger;
import com.project.inftrastructure.execution.logger.UITestListener;
import com.project.inftrastructure.execution.wait.UIWaitManager;
import com.project.inftrastructure.middlewares.ui.driver.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;

@Listeners(UITestListener.class)
public abstract class BaseUITest {
    protected TestLogger LOG;

    protected UIWaitManager waitManager = UIWaitManager.getInstance();

    @AfterMethod
    public void dropDriver() {
        WebDriverManager.stop();
    }

    // Logger
    @BeforeMethod
    public void initLogger(Method method) {
        LOG = TestLogger.getLogger(method.getName(), method.getDeclaringClass().getSimpleName());
    }
    @AfterMethod
    public void dropLogger() {
        LOG.drop();
    }
}
