package controller;

import command.Command;
import command.CommandImpl;
import model.grammar.Grammar;
import model.grammar.reader.ContextFreeGrammarReader;
import model.production.context_free.ContextFreeProduction;
import view.View;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    public Controller() {
    }

    private final View view = new View();

    private Grammar<? extends ContextFreeProduction> model = null;

    private final Map<String, Command> commands = new HashMap<>();

    private void setUp() {
        this.commands.put("1", new CommandImpl("Read a grammar from a file", () -> this.model = ContextFreeGrammarReader.readGrammar(this.view.readElement("File name: "), this.view.readElement("Symbols separator: "))));
        this.commands.put("2", new CommandImpl("Display the Non-Terminals of the grammar", () -> this.view.printCollection("The NonTerminals are: ", this.model.nonTerminals(), ", ")));
        this.commands.put("3", new CommandImpl("Display the Terminals of the grammar", () -> this.view.printCollection("The Terminals are: ", this.model.terminals(), ", ")));
        this.commands.put("4", new CommandImpl("Display the start Non-Terminal if the grammar", () -> this.view.printText("The start Non-Terminal is: ", this.model.startNonTerminal())));
        this.commands.put("5", new CommandImpl("Display the Productions of the grammar", () -> this.view.printCollection("The Productions are:\n", this.model.productions(), "\n")));
    }

    public void run() {
        this.setUp();
        this.view.printText("Context Free Grammar Visualiser");
        while (true) {
            this.view.printMenu(this.commands.entrySet()
                    .stream()
                    .map(entry -> new AbstractMap.SimpleImmutableEntry<>(entry.getKey(), entry.getValue().getName()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
            final String option = this.view.readElement("Your option: ");
            if ("x".equals(option)) {
                break;
            }
            if (this.commands.containsKey(option)) {
                Command command = this.commands.get(option);
                this.view.printText(command.getName());
                try {
                    command.execute();
                } catch (RuntimeException ex) {
                    this.view.printText(String.format("An error occurred: %s\n", ex.getMessage()));
                }
            } else {
                this.view.printText("You introduced an invalid message.\nTry again!");
            }
        }
        this.view.printText("You chose to exit the application.\nHave a nice day! Bye.");
    }
}
