import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    
    public static String platform;
    
    public static void main(String[] args) {
        // Argomenti richiesti: nome file e numero riga di cui ne si vuole sentire la lettura tramite Reader.
        if (args.length < 2) {
            System.out.println("Utilizzo: java Main <Java File> <line n.>");
            return;
        }
        // Invocando il Reader di default del sistema operativo, verifico in quale OS viene eseguito il programma [Windows, Linux, MacOs]
        checkOS();

        String filepath = args[0];
        boolean isBetweenUppers = false;
        String textForReading = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            String line;
            String contentString = "";
            int counter = 0;
            int lineNo = Integer.parseInt(args[1]);

            // Lettura dell'intero file 
            while ((line = reader.readLine()) != null) {
                // Skippo la linea fin quando non raggiungo quella desiderata
                if (counter == lineNo) {
                    line = line.trim();
                    // Linea vuota
                    if (line.length() < 1) {
                        System.out.println("");                     
                    }
                    // linea che inizierà con un commento su linea
                    else if (line.contains("//")) {
                        int commentIndex = line.indexOf("//");
                        String comment = line.substring(commentIndex);
                        comment = comment.replace("//", "");
                        String code = line.substring(0, commentIndex).trim();
                        String[] codes = code.split("((?<=\\W)|(?=\\W))"); 
                        for(String cd : codes) {
                            if (cd.equals("\"")) {
                                if (isBetweenUppers) {
                                    isBetweenUppers = false;
                                    textForReading += processWord("\"" + contentString + "\"");
                                    contentString = "";
                                }
                                else {
                                    isBetweenUppers = true;
                                }
                            }
                            else if (isBetweenUppers) {
                                contentString += cd;
                            }
                            else if (!cd.equals(" ")) {
                                textForReading += processWord(cd);
                            }
                        }
                        textForReading += ("Commento: " + comment + ". Fine commento.\n");
                    }
                    else {
                        String[] words = line.split("((?<=\\W)|(?=\\W))"); 
                        for(String word : words) {
                            // la linea è contenuta tra doppi apici  (contenuto stringa)
                            if (word.equals("\"")) {
                                if (isBetweenUppers) {
                                    isBetweenUppers = false;
                                    textForReading += processWord("\"" + contentString + "\"");
                                    contentString = "";
                                }
                                else {
                                    isBetweenUppers = true;
                                }
                            }
                            else if (isBetweenUppers) {
                                contentString += word;
                            }
                            else if (!word.equals(" ")) {
                                textForReading += processWord(word);
                            }
                        }
                    }
                    break;
                }
                else {
                    counter++;
                }
            }

            reader.close();
           
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(textForReading);
        speak(textForReading);
    }
    // Gestione dell'enfasi sulla lettura delle keyword di linguaggio
    public static String processWord(String word) {
        if (isVisibility(word)) {
            if (word.equals("public")) {
                word = "Pablic";
            }
            else if (word.equals("protected")) {
                word = "Protect";
            }
            else if (word.equals("private")) {
                word = "Prai vat";
            }
            return ("Visibilità " + word + ",\n");
        }
        else if (isType(word)) {
            return ("Tipo " + word + ",\n");
        }
        else if (word.equals("class")) {
            return "Classe ";
        }
        else if (word.equals("static")) {
            return "Componente Statico. ";
        }
        else if (word.startsWith("\"") && word.endsWith("\"")) {
            return ("Contenuto stringa: " + word + ".\n");
        }
        else if (word.equals("{")) {
            return "Aperta graffa.\n";
        }
        else if (word.equals("}")) {
            return "Chiusa graffa.\n";
        }      
        else if (word.equals("(")) {
            return "Aperta tonda.\n";
        }
        else if (word.equals(")")) {
            return "Chiusa tonda.\n";
        } 
        else if (word.equals("[")) {
            return "Aperta quadra.\n";
        }
        else if (word.equals("]")) {
            return "Chiusa quadra.\n";
        }
        else if (word.equals(";")) { 
            return ".\n";
        }
        else if (word.equals("=")) {
            return "Uguale.";
        }
        else if (word.equals("+")) {
            return "Più.";
        }
        else if (word.equals("/")) {
            return "Diviso.";
        }
        else if (word.equals("<")) {
            return "Minore.";
        }
        else if (word.equals(">")) {
            return "Maggiore.";
        }
        else if (word.equals("@")) {
            return "Chiocciola.";
        }
        else if (word.equals("?")) {
            return "Punto di domanda. ";
        }
        else if (word.equals("!")) {
            return "Non. ";
        }
        else if (word.equals("out")) {
            return "aut\u200B .\n";
        }
        else if (word.equals("println")) {
            return "Printlain" + ".\n";
        }
        else if (word.equals("length")) {
            return "Lengt" + ".\n";
        }
        else if (word.equals("Override")) {
            return "Overraid" + ".\n"; 
        }
        else if (word.equals("false")) {
            return "Fols" + ".\n"; 
        }
        else if (word.equals("true")) {
            return "True" + ".\n"; 
        }
        else if (word.equals("args")) {
            return "Args" + ".\n";
        }
        else if (word.equals("catch")) {
            return "Catch" + ".\n";
        }
        else if (word.equals("return")) {
            return "Return" + ".\n";
        }
        else if (word.equals("extends")) {
            return "Extends" + ".\n";
        }
        else if (word.equals(".")) {
            return "Punto ";
        }
        else if (word.equals(",")) {
            return "Virgola, ";
        }
        else if (word.equals("if")) {
            return "If " + ".,\n"; 
        }
        else if (word.equals("this")) {
            return "This.";
        }
        else {
            return (word.toUpperCase() + ".\n");
        }   
    }
    // Risultato lettura: "Visibilità x", con x = public, private, protected
    private static boolean isVisibility(String word) {
        String [] keywords = { "public", "private", "protected"};
        for (String keyword : keywords) {
            if (keyword.equals(word)) {
                return true;
            }
        }     
        return false;
    }
    // Risultato lettura: "Tipo x", con x il tipo analizzato
    private static boolean isType(String word) {
        String [] keywords = { "int", "float", "double", "boolean", "String", "void"};
        for (String keyword : keywords) {
            if (keyword.equals(word)) {
                return true;
            }
        }     
        return false;
    }
    // Verifica del sistema operativo su cui viene eseguito il file
    private static void checkOS() {
        String os = System.getProperty("os.name").toLowerCase(); 

        if (os.contains("win")) { 
            platform = "Windows";
        }
        else if (os.contains("mac")) {
            platform = "MacOs";
        }
        else if (os.contains("nix") || os.contains("nux")) { 
            platform = "Linux"; 
        }
        else {
            System.out.println("Sistema operativo non rilevato");
            System.exit(1);
        }

    }
    // Funzione che invoca il reader per leggere il contenuto della linea d'interesse.
    private static void speak(String word) {
        // Invocazione Reader MacOS
        if (platform.equals("MacOs")) {
            String[] command = {"say", word};

            try {
                Process process = Runtime.getRuntime().exec(command); 
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Invocazione Reader Windows
        else if (platform.equals("Windows")) {
            String command = "Add-Type -AssemblyName System.Speech; " + 
                             "$speak = New-Object System.Speech.Synthesis.SpeechSynthesizer; " + 
                             "$speak.Speak('" + word.replace("'", "''") + "');";

            try {
                ProcessBuilder processBuilder = new ProcessBuilder(); 
                processBuilder.command("powershell.exe", "-Command", command); 
                Process process = processBuilder.start();
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line; 
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Invocazione Reader Linux
        else {
            String[] command = {"espeak", word}; 

            try {
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader reader = new BufferedReader((new InputStreamReader(process.getInputStream())));
                String line; 
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
