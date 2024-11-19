@echo off
:: Assegna i parametri passati a variabili leggibili

set file=%1
set /a lineNumber=%2-1

javac Main.java 

java Main "%file%" "%lineNumber%"
