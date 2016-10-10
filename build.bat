@echo off
mkdir classes
dir *.java /s /b | findstr /v src\\test > FilesList.txt
javac @FilesList.txt -d classes
