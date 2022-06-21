package it.unicam.cs.pa.ma114763.logo.parser;

import it.unicam.cs.pa.ma114763.logo.Color;
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

/**
 * @author Lorenzo Lapucci
 */
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
        String[] strings = line.split("\\s+|\\b");
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
            tokens.addAll(getTokens(trimmed));
        }

        return tokens;
    }

    private @NotNull List<Token> getTokens(String string) throws InvalidCharactersException {
        List<Token> tokens = tokenizer.matchTokens(string);
        if (tokens.size() == 0) {
            throw new InvalidCharactersException(string);
        }
        return tokens;
    }

    private Statement getStatement(String command, List<Token> tokens) throws ParserException {
        return switch (command) {
            case "FORWARD", "BACKWARD", "FD", "BK" -> getMoveStatement(tokens, command.equals("BACKWARD") || command.equals("BK"));
            case "LEFT", "RIGHT", "LT", "RT" -> getAngleStatement(tokens, command.equals("LEFT") || command.equals("LT"));
            case "CLEARSCREEN", "CS" -> getClearScreenStatement(tokens);
            case "HOME" -> getHomeStatement(tokens);
            case "PENUP", "PENDOWN", "PU", "PD" -> getSetDrawingStatement(tokens, command.equals("PENDOWN") || command.equals("PD"));
            case "SETPENCOLOR", "SETPC" -> getSetStrokeColorStatement(tokens);
            case "SETFILLCOLOR", "SETFC" -> getSetFillColorStatement(tokens);
            case "SETSCREENCOLOR", "SETSC" -> getSetBackgroundColorStatement(tokens);
            case "SETPENSIZE" -> getSetStrokeSizeStatement(tokens);
            case "RIPETI", "REPEAT" -> getRepeatStatement(tokens);
            default -> throw new UnknownCommandException(command);
        };
    }

    private Statement getMoveStatement(List<Token> tokens, boolean backward) throws InvalidSyntaxException {
        ParserUtils.checkNumberOfArguments(tokens, 1);
        Token firstArg = tokens.get(1);
        ParserUtils.checkArgumentNumber(firstArg);

        int distance = Integer.parseInt(firstArg.text());
        return new MoveStatement(distance, backward);
    }

    private Statement getAngleStatement(List<Token> tokens, boolean subtract) throws ParserException {
        ParserUtils.checkNumberOfArguments(tokens, 1);
        Token firstArg = tokens.get(1);
        ParserUtils.checkArgumentNumber(firstArg);

        int angleRotation = Integer.parseInt(firstArg.text()) * (subtract ? -1 : 1);
        return new RotateAngleStatement(angleRotation);
    }

    private Statement getClearScreenStatement(List<Token> tokens) throws ParserException {
        ParserUtils.checkNumberOfArguments(tokens, 0);
        return new ClearScreenStatement();
    }

    private Statement getHomeStatement(List<Token> tokens) throws ParserException {
        ParserUtils.checkNumberOfArguments(tokens, 0);
        return new HomeStatement();
    }

    private Statement getSetDrawingStatement(List<Token> tokens, boolean draw) throws ParserException {
        ParserUtils.checkNumberOfArguments(tokens, 0);
        return new SetDrawingStatement(draw);
    }

    private Statement getSetStrokeColorStatement(List<Token> tokens) throws ParserException {
        ParserUtils.checkNumberOfArguments(tokens, 3, 4);
        Color color = ParserUtils.getColor(tokens);
        return new SetStrokeColorStatement(color);
    }

    private Statement getSetFillColorStatement(List<Token> tokens) throws ParserException {
        ParserUtils.checkNumberOfArguments(tokens, 3, 4);
        Color color = ParserUtils.getColor(tokens);
        return new SetFillColorStatement(color);
    }

    private Statement getSetBackgroundColorStatement(List<Token> tokens) throws ParserException {
        ParserUtils.checkNumberOfArguments(tokens, 3, 4);
        Color color = ParserUtils.getColor(tokens);
        return new SetBackgroundColorStatement(color);
    }

    private Statement getSetStrokeSizeStatement(List<Token> tokens) throws ParserException {
        ParserUtils.checkNumberOfArguments(tokens, 1);
        Token firstArg = tokens.get(1);
        ParserUtils.checkArgumentNumber(firstArg);

        int size = Integer.parseInt(firstArg.text());
        if (size < 1) {
            throw new InvalidSyntaxException(firstArg, "stroke size must be greater than 0");
        }
        return new SetStrokeSizeStatement(size);
    }

    private Statement getRepeatStatement(List<Token> tokens) throws ParserException {
        ParserUtils.checkNumberOfArgumentsAtLeast(tokens, 4);
        ParserUtils.checkArgumentNumber(tokens.get(1));
        int times = Integer.parseInt(tokens.get(1).text());
        ParserUtils.checkArgumentLBracket(tokens.get(2));
        List<Statement> statements = getRepeatInnerStatements(tokens, 3, tokens.size() - 1);
        ParserUtils.checkArgumentRBracket(tokens.get(tokens.size() - 1));
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
        ParserUtils.checkArgumentWord(tokens.get(i)); // command
        // find the end of the command
        int j = i + 1;
        int nestedBrackets = 0;
        while (j < max) {
            if (tokens.get(j).type() instanceof LBracketTokenType) nestedBrackets++;
            else if (tokens.get(j).type() instanceof RBracketTokenType) nestedBrackets--;
            if (nestedBrackets <= 0 && tokens.get(j).type() instanceof WordTokenType) {
                break;
            }
            j++;
        }
        return tokens.subList(i, j);
    }
}
