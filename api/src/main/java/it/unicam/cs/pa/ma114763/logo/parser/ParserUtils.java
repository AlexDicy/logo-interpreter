package it.unicam.cs.pa.ma114763.logo.parser;

import it.unicam.cs.pa.ma114763.logo.drawing.Color;
import it.unicam.cs.pa.ma114763.logo.drawing.RGBColor;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidSyntaxException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import it.unicam.cs.pa.ma114763.logo.parser.tokentype.LBracketTokenType;
import it.unicam.cs.pa.ma114763.logo.parser.tokentype.NumberTokenType;
import it.unicam.cs.pa.ma114763.logo.parser.tokentype.RBracketTokenType;
import it.unicam.cs.pa.ma114763.logo.parser.tokentype.WordTokenType;

import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public class ParserUtils {

    /**
     * Returns a color given a list of at least <b>4</b> tokens.
     *
     * @param tokens the list of tokens
     * @return the color
     * @throws ParserException if the list of tokens is not valid
     */
    public static Color getColor(List<Token> tokens) throws ParserException {
        int red = checkValidByte(tokens.get(1));
        int green = checkValidByte(tokens.get(2));
        int blue = checkValidByte(tokens.get(3));

        if (tokens.size() == 5) {
            return new RGBColor(red, green, blue, checkValidByte(tokens.get(4)));
        }
        return new RGBColor(red, green, blue);
    }

    /**
     * Checks whether the given list of tokens has at least <code>atLeast</code> arguments after
     * the first token.
     *
     * @param tokens  the list of tokens
     * @param atLeast the minimum number of arguments
     * @throws InvalidSyntaxException if the arguments number is less than <code>atLeast</code>
     */
    public static void checkNumberOfArgumentsAtLeast(List<Token> tokens, int atLeast) throws InvalidSyntaxException {
        int given = tokens.size() - 1;
        if (given < atLeast) {
            throw new InvalidSyntaxException(tokens.get(0).text().toUpperCase(), "takes at least " + atLeast + " arguments, but " + given + " were given");
        }
    }

    /**
     * Checks whether the given list of tokens has exactly <code>expected</code> arguments after
     * the first token.
     *
     * @param tokens   the list of tokens
     * @param expected the expected number of arguments
     * @throws InvalidSyntaxException if the arguments number is not equal to <code>expected</code>
     */
    public static void checkNumberOfArguments(List<Token> tokens, int expected) throws InvalidSyntaxException {
        int given = tokens.size() - 1;
        if (given != expected) {
            throw new InvalidSyntaxException(tokens.get(0).text().toUpperCase(), "takes " + expected + " arguments, but " + given + " were given");
        }
    }

    /**
     * Checks whether the given list of tokens has a valid number of arguments after the first token.
     *
     * @param tokens       the list of tokens
     * @param expectedList the list of possible expected numbers of arguments
     * @throws InvalidSyntaxException if the arguments number is not included in <code>expectedList</code>
     */
    public static void checkNumberOfArguments(List<Token> tokens, int... expectedList) throws InvalidSyntaxException {
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

    /**
     * Checks that the given tokens are tokens of type {@link NumberTokenType}.
     *
     * @param tokens the list of tokens to check
     * @throws InvalidSyntaxException if one of the tokens is not a number
     */
    public static void checkArgumentNumber(Token... tokens) throws InvalidSyntaxException {
        for (Token token : tokens) {
            if (!(token.type() instanceof NumberTokenType)) {
                throw new InvalidSyntaxException(token, "is not a number");
            }
        }
    }

    /**
     * Checks that the given token is a token of type {@link WordTokenType}.
     *
     * @param token the token to check
     * @throws InvalidSyntaxException if the token is not a word
     */
    public static void checkArgumentWord(Token token) throws InvalidSyntaxException {
        if (!(token.type() instanceof WordTokenType)) {
            throw new InvalidSyntaxException(token, "is not a word");
        }
    }

    /**
     * Checks that the given token is a token of type {@link LBracketTokenType}.
     *
     * @param token the token to check
     * @throws InvalidSyntaxException if the token is not a left bracket
     */
    public static void checkArgumentLBracket(Token token) throws InvalidSyntaxException {
        if (!(token.type() instanceof LBracketTokenType)) {
            throw new InvalidSyntaxException(token, "is not a left bracket '['");
        }
    }

    /**
     * Checks that the given token is a token of type {@link RBracketTokenType}.
     *
     * @param token the token to check
     * @throws InvalidSyntaxException if the token is not a right bracket
     */
    public static void checkArgumentRBracket(Token token) throws InvalidSyntaxException {
        if (!(token.type() instanceof RBracketTokenType)) {
            throw new InvalidSyntaxException(token, "is not a right bracket ']'");
        }
    }

    /**
     * Checks that the given token is a token of type {@link NumberTokenType} and that its value is
     * between <code>0</code> and <code>255</code>.
     *
     * @param token the token to check
     * @return the value of the token as a <code>byte</code>
     * @throws InvalidSyntaxException if the token is not a number or if its value is not
     *                                between <code>0</code> and <code>255</code>
     */
    public static int checkValidByte(Token token) throws InvalidSyntaxException {
        checkArgumentNumber(token);
        try {
            int value = Integer.parseInt(token.text());
            if (value < 0 || value > 255) {
                throw new InvalidSyntaxException(token, "is not a valid range for a byte, must be between 0 and 255");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException(token, "is not a valid byte, must be a number between 0 and 255");
        }
    }
}
