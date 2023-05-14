package model.grammar.reader;

import model.grammar.ContextFreeGrammar;
import model.grammar.GrammarImpl;
import model.production.Production;
import model.production.context_free.ContextFreeProduction;
import model.vocabulary.Symbol;
import model.vocabulary.nonterminal.NonTerminal;
import model.vocabulary.terminal.Terminal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

public class ContextFreeGrammarReader {
    public static ContextFreeGrammar<? extends ContextFreeProduction> readGrammar(final String fileName,
                                                                                  final String symbolsSeparator) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            final Collection<? extends NonTerminal> nonTerminals = readNonTerminals(reader, symbolsSeparator);
            final Collection<? extends Terminal> terminals = readTerminals(reader, symbolsSeparator);
            final NonTerminal startNonTerminal = readStartNonTerminal(reader);
            final Collection<? extends ContextFreeProduction> productions = readProductions(reader, symbolsSeparator,
                    nonTerminals.stream().map(Symbol::value).toList(),
                    terminals.stream().map(Symbol::value).toList());

            return new ContextFreeGrammar<>(new GrammarImpl<>(nonTerminals, terminals, productions, startNonTerminal));
        } catch (IOException ex) {
            throw new RuntimeException(String.format("while reading from file %s ", fileName), ex);
        }
    }

    private static Collection<? extends NonTerminal> readNonTerminals(final BufferedReader reader,
                                                                      final String symbolsSeparator) throws IOException {
        return Arrays.stream(readTokens(reader, symbolsSeparator)).map(NonTerminal::from).toList();
    }

    private static Collection<? extends Terminal> readTerminals(final BufferedReader reader, final String symbolsSeparator) throws IOException {
        return Arrays.stream(readTokens(reader, symbolsSeparator)).map(Terminal::from).toList();
    }

    private static Collection<? extends ContextFreeProduction> readProductions(final BufferedReader reader,
                                                                               final String symbolsSeparator,
                                                                               final Collection<? extends String> nonTerminals,
                                                                               final Collection<? extends String> terminals) throws IOException {
        return reader.lines().map(line -> parseProduction(line, symbolsSeparator, nonTerminals, terminals)).toList();
    }

    private static ContextFreeProduction parseProduction(final String stringRepresentation,
                                                         final String symbolsSeparator,
                                                         final Collection<? extends String> nonTerminals,
                                                         final Collection<? extends String> terminals
    ) {
        final String[] sides = stringRepresentation.split(Production.SIDES_SEPARATOR);

        if (sides.length < 1 || sides.length > 2) {
            throw new IllegalArgumentException("Invalid production string: " + stringRepresentation);
        }

        final Function<String, Symbol> parseFunction = symbol -> {
            if (nonTerminals.contains(symbol)) {
                return NonTerminal.from(symbol);
            }
            if (terminals.contains(symbol)) {
                return Terminal.from(symbol);
            }
            throw new IllegalArgumentException(String.format("The symbol %s does not belong to the grammar", symbol));
        };

        final Symbol[] leftSide = Arrays.stream(sides[0].split(symbolsSeparator)).map(parseFunction).toList().toArray(new Symbol[]{});

        final Symbol[] rightSide;

        if (sides.length == 1) {
            rightSide = new Symbol[0];
        } else {
            rightSide = Arrays.stream(sides[1].split(symbolsSeparator)).map(parseFunction).toList().toArray(new Symbol[]{});
        }

        return ContextFreeProduction.builder()
                .leftSide(leftSide)
                .rightSide(rightSide)
                .build();
    }

    private static NonTerminal readStartNonTerminal(final BufferedReader reader) throws IOException {
        return NonTerminal.from(readLine(reader));
    }

    private static String[] readTokens(final BufferedReader reader, final String separator) throws IOException {
        return readLine(reader).split(separator);
    }

    private static String readLine(final BufferedReader reader) throws IOException {
        return reader.readLine().trim();
    }
}
