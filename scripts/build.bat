@echo off
setlocal enabledelayedexpansion

:: ----- Configuration -----
set SOAPUI_HOME=C:\Program Files\SmartBear\SoapUI-5.7.2
set SRC_DIR=src
set OUT_DIR=target
set PACKAGE_NAME=soapui-tclogger.jar

:: ----- Clean target directory -----
cd ..
echo Cleaning target directory...
if exist "%OUT_DIR%" rmdir /s /q "%OUT_DIR%"
mkdir "%OUT_DIR%"

:: ----- Compile source files -----
echo Compiling Java source files...
"%JAVA_HOME%\bin\javac.exe" ^
  -classpath "%SOAPUI_HOME%\bin\*;%SOAPUI_HOME%\lib\*" ^
  -d "%OUT_DIR%" ^
  %SRC_DIR%\io\github\adam1lake\plugins\*.java ^
  %SRC_DIR%\io\github\adam1lake\listeners\*.java

if %errorlevel% neq 0 (
    echo Compilation failed. Aborting build.
    exit /b %errorlevel%
)

:: ----- Create JAR -----
echo Creating JAR file...
"%JAVA_HOME%\bin\jar.exe" cvf "%PACKAGE_NAME%" -C "%OUT_DIR%" .

:: ----- Done -----
echo Build completed successfully.
pause