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
  - /textbf{Le letture possono dipendere dal contesto}: il Reader deve riuscire a diversificare la lettura se si trova di fronte a questi 3 scenari: `int a = 3;` `"int a  = 3;"` `//int a = 3;`; 
