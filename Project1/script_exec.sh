#!/bin/bash

file_name=$1
line_number=$2

line_number=$((line_number-1))

# Compila il file Main.java
javac Main.java

# Esegui la classe Main con i parametri forniti
java Main "$file_name" "$line_number"
