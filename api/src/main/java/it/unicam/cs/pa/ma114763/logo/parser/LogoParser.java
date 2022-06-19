package it.unicam.cs.pa.ma114763.logo.parser;

import it.unicam.cs.pa.ma114763.logo.Color;
import it.unicam.cs.pa.ma114763.logo.RGBColor;
import it.unicam.cs.pa.ma114763.logo.Statement;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidCharactersException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidSyntaxException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.UnknownCommandException;
import it.unicam.cs.pa.ma114763.logo.parser.tokentype.*;
import it.unicam.cs.pa.ma114763.logo.statement.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LogoParser implements Parser {
    private static final List<TokenType> types = List.of(
            new NumberTokenType(),
            new DecimalTokenType(),
            new WordTokenType(),
            new LBracketTokenType(),
            new RBracketTokenType()
    );

    private final Tokenizer tokenizer;

    public LogoParser() {
        this.tokenizer = new LogoTokenizer(types);
    }

    @Override
    public List<Statement> parse(String input) throws ParserException {
        List<Statement> statements = new ArrayList<>();
        // every line is parsed as a statement
        String[] lines = input.split("\n");
        for (String line : lines) {
            String trimmed = line.trim();
            // skip if empty line or a comment
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue;
            }
            statements.add(parseLine(trimmed));
        }
        return statements;
    }

    private Statement parseLine(String line) throws ParserException {
        // split the input string on whitespace
        String[] strings = line.split("\\s+");
        List<Token> tokens = getTokens(strings);
        // the first token is the command, and should be a word
        Token command = tokens.get(0);
        if (!(command.type() instanceof WordTokenType)) {
            throw new ParserException("Invalid command start syntax: " + command.text());
        }
        return getStatement(command.text().toUpperCase(), tokens);
    }

    protected List<Token> getTokens(String[] strings) throws InvalidCharactersException {
        List<Token> tokens = new ArrayList<>();

        for (String string : strings) {
            String trimmed = string.trim();
            // skip empty strings
            if (trimmed.isEmpty()) {
                continue;
            }
            tokens.add(getToken(trimmed));
        }

        return tokens;
    }

    private @NotNull Token getToken(String string) throws InvalidCharactersException {
        Token token = tokenizer.matchToken(string);
        if (token == null) {
            throw new InvalidCharactersException(string);
        }
        return token;
    }

    private Statement getStatement(String command, List<Token> tokens) throws ParserException {
        return switch (command) {
            case "FORWARD", "BACKWARD" -> getMoveStatement(tokens, command.equals("BACKWARD"));
            case "LEFT", "RIGHT" -> getAngleStatement(tokens, command.equals("LEFT"));
            case "CLEARSCREEN" -> getClearScreenStatement(tokens);
            case "HOME" -> getHomeStatement(tokens);
            case "PENUP", "PENDOWN" -> getSetDrawingStatement(tokens, command.equals("PENDOWN"));
            case "SETPENCOLOR" -> getSetStrokeColorStatement(tokens);
            case "SETFILLCOLOR" -> getSetFillColorStatement(tokens);
            case "SETSCREENCOLOR" -> getSetBackgroundColorStatement(tokens);
            case "SETPENSIZE" -> getSetStrokeSizeStatement(tokens);
            case "RIPETI" -> getRepeatStatement(tokens);
            default -> throw new UnknownCommandException(command);
        };
    }

    private Statement getMoveStatement(List<Token> tokens, boolean backward) throws InvalidSyntaxException {
        checkNumberOfArguments(tokens, 1);
        Token firstArg = tokens.get(1);
        checkArgumentNumber(firstArg);

        int distance = Integer.parseInt(firstArg.text());
        return new MoveStatement(distance, backward);
    }

    private Statement getAngleStatement(List<Token> tokens, boolean subtract) throws ParserException {
        checkNumberOfArguments(tokens, 1);
        Token firstArg = tokens.get(1);
        checkArgumentNumber(firstArg);

        int angleIncrement = Integer.parseInt(firstArg.text()) * (subtract ? -1 : 1);
        return new IncrementAngleStatement(angleIncrement);
    }

    private Statement getClearScreenStatement(List<Token> tokens) throws ParserException {
        checkNumberOfArguments(tokens, 0);
        return new ClearScreenStatement();
    }

    private Statement getHomeStatement(List<Token> tokens) throws ParserException {
        checkNumberOfArguments(tokens, 0);
        return new HomeStatement();
    }

    private Statement getSetDrawingStatement(List<Token> tokens, boolean draw) throws ParserException {
        checkNumberOfArguments(tokens, 0);
        return new SetDrawingStatement(draw);
    }

    private Statement getSetStrokeColorStatement(List<Token> tokens) throws ParserException {
        checkNumberOfArguments(tokens, 3, 4);
        Color color = getColor(tokens);
        return new SetStrokeColorStatement(color);
    }

    private Statement getSetFillColorStatement(List<Token> tokens) throws ParserException {
        checkNumberOfArguments(tokens, 3, 4);
        Color color = getColor(tokens);
        return new SetFillColorStatement(color);
    }

    private Statement getSetBackgroundColorStatement(List<Token> tokens) throws ParserException {
        checkNumberOfArguments(tokens, 3, 4);
        Color color = getColor(tokens);
        return new SetBackgroundColorStatement(color);
    }

    private Statement getSetStrokeSizeStatement(List<Token> tokens) throws ParserException {
        checkNumberOfArguments(tokens, 1);
        Token firstArg = tokens.get(1);
        checkArgumentNumber(firstArg);

        int size = Integer.parseInt(firstArg.text());
        if (size < 1) {
            throw new InvalidSyntaxException(firstArg, "stroke size must be greater than 0");
        }
        return new SetStrokeSizeStatement(size);
    }

    private Statement getRepeatStatement(List<Token> tokens) throws ParserException {
        checkNumberOfArgumentsAtLeast(tokens, 4);
        checkArgumentNumber(tokens.get(1));
        int times = Integer.parseInt(tokens.get(1).text());
        checkArgumentLBracket(tokens.get(2));
        List<Statement> statements = getRepeatInnerStatements(tokens, 3, tokens.size() - 1);
        checkArgumentRBracket(tokens.get(tokens.size() - 1));
        return new RepeatStatement(times, statements);
    }

    private List<Statement> getRepeatInnerStatements(List<Token> tokens, int startIndex, int endIndex) throws ParserException {
        List<Statement> statements = new ArrayList<>();
        int i = startIndex;

        while (i < endIndex) {
            List<Token> subTokens = getNextRepeatStatementTokens(tokens, i, endIndex);
            statements.add(getStatement(subTokens.get(0).text().toUpperCase(), subTokens));
            i += subTokens.size();
        }
        return statements;
    }

    private List<Token> getNextRepeatStatementTokens(List<Token> tokens, int i, int max) throws ParserException {
        checkArgumentWord(tokens.get(i)); // command
        // find the end of the command
        int j = i + 1;
        while (j < max && !(tokens.get(j).type() instanceof WordTokenType)) {
            j++;
        }
        return tokens.subList(i, j);
    }

    /**
     * Returns a color given a list of at least <b>4</b> tokens.
     *
     * @param tokens the list of tokens
     * @return the color
     * @throws ParserException if the list of tokens is not valid
     */
    private Color getColor(List<Token> tokens) throws ParserException {
        byte red = checkValidByte(tokens.get(1));
        byte green = checkValidByte(tokens.get(2));
        byte blue = checkValidByte(tokens.get(3));

        if (tokens.size() == 5) {
            return new RGBColor(red, green, blue, checkValidByte(tokens.get(4)));
        }
        return new RGBColor(red, green, blue);
    }

    private void checkNumberOfArguments(List<Token> tokens, int expected) throws InvalidSyntaxException {
        int given = tokens.size() - 1;
        if (given != expected) {
            throw new InvalidSyntaxException(tokens.get(0).text().toUpperCase(), "takes " + expected + " arguments, but " + given + " were given");
        }
    }

    private void checkNumberOfArgumentsAtLeast(List<Token> tokens, int atLeast) throws InvalidSyntaxException {
        int given = tokens.size() - 1;
        if (given < atLeast) {
            throw new InvalidSyntaxException(tokens.get(0).text().toUpperCase(), "takes at least " + atLeast + " arguments, but " + given + " were given");
        }
    }

    private void checkNumberOfArguments(List<Token> tokens, int... expectedList) throws InvalidSyntaxException {
        int given = tokens.size() - 1;
        for (int expected : expectedList) {
            if (given == expected) {
                return;
            }
        }
        StringBuilder b = new StringBuilder();
        for (int expected : expectedList) { // format expectedList to "1 or 2 or 3"
            b.append(expected);
            b.append(" or ");
        }
        b.delete(b.length() - 2, b.length());
        throw new InvalidSyntaxException(tokens.get(0).text().toUpperCase(), "takes " + b + " arguments, but " + given + " were given");
    }

    private void checkArgumentNumber(Token... tokens) throws InvalidSyntaxException {
        for (Token token : tokens) {
            if (!(token.type() instanceof NumberTokenType)) {
                throw new InvalidSyntaxException(token, "is not a number");
            }
        }
    }

    private void checkArgumentWord(Token token) throws InvalidSyntaxException {
        if (!(token.type() instanceof WordTokenType)) {
            throw new InvalidSyntaxException(token, "is not a word");
        }
    }

    private void checkArgumentLBracket(Token token) throws InvalidSyntaxException {
        if (!(token.type() instanceof LBracketTokenType)) {
            throw new InvalidSyntaxException(token, "is not a left bracket '['");
        }
    }

    private void checkArgumentRBracket(Token token) throws InvalidSyntaxException {
        if (!(token.type() instanceof RBracketTokenType)) {
            throw new InvalidSyntaxException(token, "is not a right bracket ']'");
        }
    }

    private byte checkValidByte(Token token) throws InvalidSyntaxException {
        checkArgumentNumber(token);
        try {
            return Byte.parseByte(token.text());
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException(token, "is not a valid byte, must be between 0 and 255");
        }
    }
}
