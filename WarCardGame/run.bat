@echo off
set "JAVA_HOME=C:\Program Files\Java\jdk1.8.0_202"
set "JAVAFX_PATH=C:\Program Files\Java\jre1.8.0_441\lib"

echo Compiling Java files...
"%JAVA_HOME%\bin\javac" -cp "%JAVAFX_PATH%\jfxswt.jar" -d bin src\*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)

echo Running program...
"%JAVA_HOME%\bin\java" -cp "bin;%JAVAFX_PATH%\jfxswt.jar" WarGameApp
pause
