@echo off
echo Cleaning previous compilation...
if exist "*.class" del *.class

echo Compiling Java files...
javac *.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Running program...
    echo.
    java Main
) else (
    echo.
    echo Compilation failed!
    pause
)