# JavaTextToSpeech
In questo progetto verranno raggruppati tutti i contenuti raccolti nel tempo riguardo alla possibilità di utilizzare la tecnica `TextToSpeech` in aiuto a programmatori ipovedenti,
con specifico focus sul linguaggio `Java`.

## Opzione 1 
Il primo progetto propone di utilizzare il file [Main.java](/Project1/Main.java). L'idea del codice è quella di leggere tutto il file di cui ne si vuole ascoltare il contenuto ed andare ad estrarre la riga desiderata. Pertanto, il programma necessita come argomenti il numero della riga ed il nome del programma da analizzare. 
Dopo ciò, il programma andrà a rilevare su quale sistema operativo sta venendo eseguito [MacOs, Windows, Linux], in modo da utilizzare il relativo comando per invocare lo specifico Reader di default installato. 
Estratta la riga, verranno analizzate tutte le parole presenti. Se una parola è una keyword del lingaggio Java, ne si andrà ad enfatizzare la lettura. 
Con enfasi si intende che, utilizzando segni di punteggiatura, spazi, lettere maiuscole, si possono ottenere letture differenti da parte del Reader, in particolare si riescono ad inserire delle pause che aiutano alla comprensione del contenuto rispetto ad una lettura monotona e senza stop. 

### Vantaggi
  -  Costruire per ogni specifico costrutto un'enfasi associata (quali segni di punteggiatura leggere, come leggere una certa parola, analizzarne il contesto).
  -  Letture rapide (l'invocazione di letture ripetute su righe differenti non ha grandi valori di delay).
  -  Adattabile a molte piattaforme (tutte quelle che possiedono uno screen reader di default).
  -  Approccio indipendente dal linguaggio (il codice è scritto in Java, ma questo non è un vincolo al funzionamento). 

### Possibili miglioramenti 
Vi sono alcuni aspetti che richiedono sviluppo all'interno del codice: 
  - Le letture possono dipendere dal contesto: il Reader deve riuscire a diversificare la lettura se si trova di fronte, ad esempio, a questi 3 scenari: `int a = 3;` `"int a  = 3;"` `//int a = 3;`; Analizzare tutti i contesti in cui un utente si può imbattere programmando e farli riconoscere a qualcuno che non può utilizzare la vista.
  - Vocabolario di tutte le keyword del linguaggio: occorre cercare per ogni singola parola del linguaggio Java il miglior modo in cui essa possa venire enfatizzata. Considerando un'implementazione che ne favorisca la chiarezza, la comprensione. Alcuni screen reader se impostati sulla lingua italiana tendono a leggere le parole inglesi senza badare alla pronuncia, occorrono dei metodi per aggirare il problema (`private` potrebbe diventare `praivat`).


## Opzione 2
Sulla stessa linea del progetto 1, si è pensato di produrre un programma scritto in Python, in cui la principale differenza rispetto al precedente è quella di sfruttare una libreria per il `TextToSpeech` chiamata `pyttsx3`. 

### Vantaggi 
Il principale vantaggio di questo metodo è quello di avere un Reader indipendente dal tipo di sitema su cui verrà eseguito. A prescindere dal contesto in cui il programma verrà lanciato, i requisiti minimi di questo approccio sono aver installato `Python` o `Python3` ed aver scaricato la libreria `pyttsx3` tramite comando: 
```
pip install pyttsx3
```

### Possibili miglioramenti 
In termini di prestazioni, questo metodo non regge il confronto col precedente dal punto di vista di letture ripetute. 
Infatti, la libreria per il TextToSpeech ad ogni invocazione necessita di un inizializzaizone dell'engine. Questo processo può durare fino ad un massimo di 10 secondi, solo dopo aver svolto tale azione il Reader leggerà il contenuto della riga specificata. 
Pertanto, se si vuole utilizzare questo progetto, occorre elaborare un architettura che tenga conto di quest'aspetto, come ad esempio un sistema client-server dove la libreria viene avviata una singola volta sul server e resta disponibile a risolvere un certo numero di richieste da parte del client, oppure utilizzare un sistema di thread. 
Il codice è disponibile in [Main.py](/Project2/Main.py)

## Opzione 3
L'ultima opzione può essere uno spunto interessante rispetto al problema di riuscire a copire tutti i costrutti dell'intero linguaggio. 
É presente al link `https://github.com/antlr/grammars-v4/tree/master/java/java20` del codice relativo ad un Lexer e ad un Parser scritti per Java, utilizzando la grammatica di `antlr`. Una volta installato ANTLR sul proprio sistema ed aver eseguito i comandi: 
```
antlr4 Java20Lexer.g4
antlr4 Java20Parser.g4
```
Verranno generati una serie di file `.java` che vanno ad implementare tutta la struttura sintattica e semantica del linguaggio. 
In particolare, nel file `Java20Parser.java`, vi sono presente delle funzioni, come `makeRuleNames`, `makeLiteralNames` che vanno ad elaborare l'output che si deve produrre in corrispondenza di un certo costrutto. 
Questi file garantiscono di non tralasciare alcun costrutto del linguaggio, inoltre possiedono già la logica necessaria per andare ad evidenziare le specifiche parole chiavi di quest ultimo. Il principale lavoro in questo caso è andare a modificare tutti gli output in modo che risultino maggiormente comprensibili ad utenti ipovedenti.





