@echo off
set "JAVA_HOME=C:\Program Files\Java\jdk1.8.0_202"

echo Compiling Java files...
"%JAVA_HOME%\bin\javac" -encoding UTF-8 -d bin src\*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)

echo Running program...
"%JAVA_HOME%\bin\java" -cp "bin" QuizApp
pause
