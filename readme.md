# SoapUI Test Case Logger plugin (soapui-tclogger)
## Information
The SoapUI Test Case Logger plugin is a simple plugin to write to a log file when test cases and test steps are started/finished. 
The plugin has only been tested in the following environment:
- Windows 11
- SoapUI Open Source 5.7.2
- JAR file built with JDK 11

## Usage
1. [Download the latest plugin JAR](https://github.com/adam1lake/soapui-tclogger/releases/latest) and place it in `%USERPROFILE%\.soapuios\plugins` (i.e. `C:\Users\<your user>\.soapuios\plugins`).
2. Run SoapUI
3. Check in the SoapUI logs that soapui-tclogger.jar has been loaded.
4. Run a test case or test suite. **Please note that running individual test steps will NOT result in logs.**
5. Check `%USERPROFILE%\.soapuios\logs` (i.e. `C:\Users\<your user>\.soapuios\logs`). You should see `test-execution.log`.

# Modification
- A build script for Windows is provided. Ensure `JAVA_HOME` is set correctly for your JDK 11 installation.
- The script has a variable `SOAPUI_HOME` that assumes your SoapUI installation is the default `C:\Program Files\SmartBear\SoapUI-5.7.2`.
- The TCLogger.java code can be modified to change the Log4j2 configuration as desired. Ensure to rebuild the JAR then follow the usage steps.