package it.unicam.cs.pa.ma114763.logo.parser;

import it.unicam.cs.pa.ma114763.logo.statement.Statement;

import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public record SingleParseResult(Statement statement, List<Token> tokens) {
}
