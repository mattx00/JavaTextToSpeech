import sys, platform, re
import pyttsx3


def checkOS(): 
    os = platform.system().lower() 
    if "win" in os: 
        return  "Windows"
    elif "mac" in os: 
        return "MacOs"
    elif "nix" in os or "nux" in os: 
        return "Linux"
  
    
def isVisibility(word): 
    keywords = ["public", "private", "protected"]
    if word in keywords: 
        return True 
    else: 
        return False    
    
def isType(word): 
    keywords = ["int", "float", "double", "boolean", "String", "void"]
    if word in keywords: 
        return True 
    else: 
        return False 
    
           
def processWord(word): 
    if isVisibility(word):
        if word == "public":
            word = "Pablic"        
        elif word == "protected":
            word = "Protect"        
        elif word == "private": 
            word = "Prai vat"
            
        return "Visibilità " + word + ",\n"
        
    elif isType(word):
        return "Tipo " + word + ",\n"
            
    elif word == "class":
        return "Classe "
            
    elif word == "static":
        return "Componente Statico. "
            
    elif word.startswith("\"") and word.endswith("\""): 
        return "Contenuto stringa: " + word + ".\n"

    elif word == "{": 
        return "Aperta graffa.\n"
    
    elif word == "}": 
        return "Chiusa graffa.\n"
        
    elif word == "(": 
        return "Aperta tonda.\n"
    
    elif word == ")": 
        return "Chiusa tonda.\n"
    
    elif word == "[": 
        return "Aperta quadra.\n"
    
    elif word == "]": 
        return "Chiusa quadra.\n"
    
    elif word == "": 
        return ".\n"
    
    elif word == "=": 
        return "Uguale."
    
    elif word == "+": 
        return "Più."
    
    elif word == "/": 
        return "Diviso."
    
    elif word == "<": 
        return "Minore."
    
    elif word == ">":
        return "Maggiore."
    
    elif word == "@":
        return "Chiocciola."
    
    elif word == "?": 
        return "Punto di domanda. "
    
    elif word == "!": 
        return "Non. "
    
    elif word == "out": 
        return "aut\u200B .\n"
    
    elif word == "println": 
        return "Printlain" + ".\n"
    
    elif word == "length": 
        return "Lengt" + ".\n"
    
    elif word == "Override": 
        return "Overraid" + ".\n" 
    
    elif word == "false": 
        return "Fols" + ".\n" 
    
    elif word == "true": 
        return "True" + ".\n" 
    
    elif word == "args": 
        return "Args" + ".\n"
    
    elif word == "catch": 
        return "Catch" + ".\n"
    
    elif word == "return": 
        return "Return" + ".\n"
    
    elif word == "extends": 
        return "Extends" + ".\n"
    
    elif word == ".": 
        return "Punto "
    
    elif word == ",": 
        return "Virgola, "
    
    elif word == "if": 
        return "If " + ".,\n" 
    
    elif word == "this":
        return "This."
    
    else: 
        return word.upper() + ".\n"
 
 
def speak(text): 
    engine = pyttsx3.init()
    engine.setProperty('rate', 150)
    engine.setProperty('volume', 1)
    
    engine.say(text)
    engine.runAndWait()
 
if __name__ == '__main__':                   
    if len(sys.argv) < 2: 
        print("Usage: python Main.py <Java File> <lineno>")
        exit(1)
        
    OS = checkOS()

    filepath = sys.argv[1]
    isBetweenUppers = False 
    textForReading = ""
    lineNo = int(sys.argv[2])

    try:
        with open(filepath, 'r', encoding='utf-8') as reader: 
            contentString = ""
            counter = 0
            
            for line in reader: 
                if counter == lineNo:        
                    line = line.strip() 
                    
                    if len(line) < 1: 
                        print("")
                    elif "//" in line: 
                        commentIndex = line.index("//")
                        comment = line[commentIndex:].replace("//", "")
                        code = line[:commentIndex].strip() 
                        codes = re.split(r'(\W)', code)
                        
                        for cd in codes: 
                            if cd == "\"": 
                                if isBetweenUppers: 
                                    isBetweenUppers = False 
                                    textForReading += processWord(f"\"{contentString}\"")
                                    contentString = ""
                                else: 
                                    isBetweenUppers = True 
                            elif isBetweenUppers: 
                                contentString += cd         
                            elif cd != " ": 
                                textForReading += processWord(cd)
                        
                        textForReading += f"Commento: {comment}. Fine commento. "
                    else: 
                        words = re.split(r'(\W)', line)
                        for word in words: 
                            if word == "\"": 
                                if isBetweenUppers: 
                                    isBetweenUppers = False 
                                    textForReading += processWord(f"\"{contentString}\"")
                                    contentString = ""
                                else: 
                                    isBetweenUppers = True 
                            elif isBetweenUppers: 
                                contentString += word
                            elif word != " ": 
                                textForReading += processWord(word)                        
                    break 
                else: 
                    counter += 1    
                        
    except IOError as e: 
        print(f"Errore nella lettura del file: {e}")

        
    print(textForReading)
    speak(textForReading)
