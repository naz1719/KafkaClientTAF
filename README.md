
# 90PoE Test task
### Steps to run tests
1. Run TestEnv: `test-env/launchTestEnv.sh`
1. Run DB: `test-env/launchDB.bat`
2. Run Test: `test-env/runTests.bat`
3. Test results from container will move to local, open newly created `\allure-report\index.html`

## Prerequisites
 * Java 11
  
## Built With
  * [TestNG](http://testng.org/doc/) - TTestNG is a testing framework designed to simplify a broad range of testing needs, from unit testing (testing a class in isolation of the others) to integration testing (testing entire systems made of several classes, several packages and even several external frameworks, such as application servers).
  * [Gradle]
  * [AllureReport 2.0 Framework](https://github.com/allure-framework/) - A flexible lightweight multi-language test report tool.
  * [Log4j](https://logging.apache.org/log4j/2.x/) - A flexible lightweight logger.

          
## Authors
* **Khimin Nazar** - *Initial work* - [Khimin Nazar](https://github.com/naz1719)
