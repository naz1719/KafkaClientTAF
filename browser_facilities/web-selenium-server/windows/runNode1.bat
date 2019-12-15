@echo off
echo -----------------------------------------------------------------------------
echo Selenium2 Grid node1 - Started: %date% - %time%
echo Parameters: hub=localhost:4444 browser=Chrome or Internet Explorer
echo -----------------------------------------------------------------------------
java -Dwebdriver.chrome.driver=../../web-drivers/windows/chromedriver.exe -Dwebdriver.ie.driver=../../web-drivers/windows/IEDriverServer32.exe -jar ../selenium-server-standalone-3.11.0.jar -role node -nodeConfig ../config/nodeConfig1.json
