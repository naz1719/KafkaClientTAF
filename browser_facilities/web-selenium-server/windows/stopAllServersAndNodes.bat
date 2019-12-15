@echo off
echo -----------------------------------------------------------------------------
echo Stopping Selenium2 Grid node - Started: %date% - %time%
echo -----------------------------------------------------------------------------
taskkill /f /im "chrome.exe" 2> nul
taskkill /f /im "WerFault.exe" 2> nul
taskkill /f /FI "USERNAME ne NT AUTHORITY\SYSTEM" /im "java.exe" 2> nul
taskkill /f /FI "USERNAME ne NT AUTHORITY\SYSTEM" /im "conhost.exe" 2> nul
taskkill /f /im "chrome.exe" 2> nul
taskkill /f /im "WerFault.exe" 2> nul
exit /b 0