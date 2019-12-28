
# UI and APU gTAA (Generic test automation architecture)

### Run
gradle test

Debug mode
gradle test -Dorg.gradle.debug=true

### CI and Docker
## Steps to run tests on Jenkins
1. Navigate to `/CD/docker` path
2. Run following: `docker-compose -f jenkins-docker-compose.yml up --build --abort-on-container-exit`
3. In Jenkins `Select custom plugins -> None - > Next`
4. Create

## Prerequisites
 * Java 1.8
  
## Built With
  * [Selenium WebDriver](http://www.seleniumhq.org/docs/03_webdriver.jsp) - Selenium is a suite of tools to automate web browsers across many platforms. 
  * [TestNG](http://testng.org/doc/) - TTestNG is a testing framework designed to simplify a broad range of testing needs, from unit testing (testing a class in isolation of the others) to integration testing (testing entire systems made of several classes, several packages and even several external frameworks, such as application servers).
  * [Gradle]
  * [AllureReport 2.0 Framework](https://github.com/allure-framework/) - A flexible lightweight multi-language test report tool.
  * [Log4j](https://logging.apache.org/log4j/2.x/) - A flexible lightweight logger.
  * [Selenium Grid](https://www.seleniumhq.org/docs/07_selenium_grid.jsp) - Selenium-Grid allows you run your tests on different machines against different browsers in parallel.
  * [Apache POI](https://poi.apache.org/) - Library for reading and writing files in Microsoft Office formats.

          
## Authors
* **Khimin Nazar** - *Initial work* - [Khimin Nazar](https://github.com/naz1719)
