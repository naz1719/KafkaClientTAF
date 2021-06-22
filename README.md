
# 90PoE Test task
## Env
Java 11
### Steps to run tests
1. Run following: `docker-compose -f jenkins-docker-compose.yml up --build --abort-on-container-exit`
2. Run following: `gradle test`
3. Open HTML report by path `build\reports\allure-report\index.html`
Debug mode
`gradle test -Dorg.gradle.debug=true`

## Prerequisites
 * Java 11
  
## Built With
  * [TestNG](http://testng.org/doc/) - TTestNG is a testing framework designed to simplify a broad range of testing needs, from unit testing (testing a class in isolation of the others) to integration testing (testing entire systems made of several classes, several packages and even several external frameworks, such as application servers).
  * [Gradle]
  * [AllureReport 2.0 Framework](https://github.com/allure-framework/) - A flexible lightweight multi-language test report tool.
  * [Log4j](https://logging.apache.org/log4j/2.x/) - A flexible lightweight logger.

          
## Authors
* **Khimin Nazar** - *Initial work* - [Khimin Nazar](https://github.com/naz1719)
