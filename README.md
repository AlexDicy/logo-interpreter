# Logo Interpreter
Logo language interpreter - University project

#### Example output
![output example](https://user-images.githubusercontent.com/11839341/175091608-f598db64-9b4a-48c0-b092-93f418987657.png)
#### Example input
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

### Sviluppo Base (Valutazione massima 22)
Il progetto consegnato a questo livello di sviluppo dovrà:
- Definire una gerarchia di classi per rappresentare il programma Logo e le istruzioni;
  - Statement.
- Definire una gerarchia di classi per rappresentare il risultato dell’esecuzione di un programma logo;
  - DrawingContext, DrawingCanvas, Shape (Line, Polygon).
- Definire una gerarchia di classi per controllare l’esecuzione di un programma Logo;
  - Interpreter, Processor.
- Definire le classi per leggere un programma da file;
  - Parser, Token, TokenType, Tokenizer, Reader.
- Definire le classi per scrivere il risultato dell’esecuzione del programma.
  - Writer.
