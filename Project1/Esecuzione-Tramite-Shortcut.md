# Eseguire il programma tramite combinazione di tasti 
Può risultare molto agibile a chi non può utilizzare la vista per programmare andare a ragruppare tutta la fase di compilazione ed esecuzione in una singola combinazione di tasti. 
Per fare ciò, occorrono una serie di pre requisiti. 

## 1. Utilizzo dell'editor 'Visual Studio Code'
Scaricare `Visual Studio Code`, l'utilizzo di questo editor è importante perchè mette a disposizione una serie di file di configurazione per le proprie shortcut che andranno a facilitare l'esecuzione del file Reader.

## 2. Installazione di `javac` e `java`
Occorreranno i relativi strumenti per effettuare la compilazione ed escuzione di un file Java da linea di comando. 

### Ubuntu/Debian (Linux)
```
sudo apt update
sudo apt install openjdk-x-jdk -y
javac -version
java -version
```

### MacOS 
```
brew install openjdk@x
sudo ln -sfn $(brew --prefix openjdk@x)/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk
export PATH="/usr/local/opt/openjdk@x/bin:$PATH"
javac -version
java -version
```


### Windows 
Occorre installare il jdk dal sito Oracle, seguendo tutte le istruzioni a schermo.
```
javac -version
java -version
```

## Configurazione dei file .json
In un nuovo progetto su Visual Studio Code, andare ad aprire la cartella `.vscode`. All'interno, vi saranno presenti 3 file:
[settings.json](/), [tasks.json](/), [shrtcts.json](/) (se non presenti, occorre crearli). Copiare all'interno di questi file il 
contenuto di quelli presenti in questa directory.



