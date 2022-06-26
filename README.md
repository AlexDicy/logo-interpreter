# [Logo Interpreter](https://github.com/AlexDicy/logo-interpreter)
Logo language interpreter - University project

### Table of contents
* [Examples](#example-output)
* [How to run](#running-the-interpreter)

### Example
###### Example output
![output example](https://user-images.githubusercontent.com/11839341/175091608-f598db64-9b4a-48c0-b092-93f418987657.png)
###### Example input
```css
# Figura 1
SETPENCOLOR 255 127 0 127
SETFILLCOLOR 255 127 0 12
HOME
PENUP
RIGHT 180
FORWARD 50
RIGHT 90
FORWARD 80
LEFT 270
PENDOWN
RIPETI 36 [RIPETI 90 [FORWARD 5 RIGHT 4] RIGHT 10]

# Figura 2
SETPENCOLOR 0 127 255 127
SETFILLCOLOR 0 127 255 12
HOME
PENUP
RIGHT 0
FORWARD 140
LEFT 0
PENDOWN
RIPETI 8 [RIGHT 45 RIPETI 6 [RIPETI 90 [FORWARD 2 RIGHT 2] RIGHT 90]]

# Figura 3
PENUP
HOME
RIGHT 180
FORWARD 220
PENDOWN
RIPETI 18 [RIPETI 5 [RIGHT 40 FORWARD 100 RIGHT 120] RIGHT 20]
```

## Running the interpreter
### CLI (Non interactive)
###### Using gradle (Keep in mind that the working directory is: `./cli/`)
```bash
$ ./gradlew :cli:run --args="-i input.txt -o output.logo -s 512x512"
```
###### Using the jar file
```bash
$ java --enable-preview -jar cli.jar -i input.txt -o output.logo -s 512x512
```
Show help
```bash
$ ./gradlew :cli:run --args="-h"
```
### CLI (Interactive)
###### Using gradle (Keep in mind that the working directory is: `./cli/`)
```bash
$ ./gradlew --console plain :cli:run
```
###### Using the jar file
```bash
$ java --enable-preview -jar cli.jar
```
### GUI
###### Using gradle
```bash
$ ./gradlew :ui:run
```
###### Using the jar file
```bash
$ java --enable-preview -jar ui.jar
```

### Sviluppo Base (Valutazione massima 22)
See: [CLI (Non-interactive)](#cli-non-interactive)

Il progetto consegnato a questo livello di sviluppo dovrà:
- Definire una gerarchia di classi per rappresentare il programma Logo e le istruzioni;
  - Statement.
- Definire una gerarchia di classi per rappresentare il risultato dell’esecuzione di un programma logo;
  - DrawingContext, DrawingCanvas, Shape (Line, Polygon).
- Definire una gerarchia di classi per controllare l’esecuzione di un programma Logo;
  - Interpreter, Processor.
- Definire le classi per leggere un programma da file;
  - Parser, Token, TokenType, Tokenizer, ResourceReader.
- Definire le classi per scrivere il risultato dell’esecuzione del programma.
  - ResourceWriter.

### Implementazione Media (Valutazione massima 25)
See: [CLI (Interactive)](#cli-interactive)

### Implementazione Avanzata (Valutazione massima 30 e Lode)
See: [GUI](#gui)
