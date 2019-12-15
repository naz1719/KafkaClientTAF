@echo off
echo -----------------------------------------------------------------------------
echo Selenium2 Grid node2 - Started: %date% - %time%
echo Parameters: hub=localhost:4444 browser=Chrome or Internet Explorer
echo -----------------------------------------------------------------------------
:: taskkill /f /im "chromedriver.exe"
echo -----------------------------------------------------------------------------
java -Dwebdriver.chrome.driver=../chromedriver.exe -jar ../selenium-server-standalone-3.11.0.jar -role node -nodeConfig ../config/nodeConfig2.json