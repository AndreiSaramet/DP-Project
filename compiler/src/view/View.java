package view;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

public class View {
    public void printMenu(Map<String, String> options) {
        System.out.println("Menu.");
        options.forEach((key, value) -> System.out.printf("%s. %s\n", key, value));
    }

    public void printText(final String text) {
        System.out.println(text);
    }

    public String readElement(final String message) {
        System.out.print(message);
        return new Scanner(System.in).nextLine();
    }

    public <T> void printCollection(final String message, final Collection<T> collection, final String delimiter) {
        System.out.print(message);
        System.out.println(String.join(delimiter, collection.stream().map(Object::toString).toList()));
    }

    public <T> void printText(final String message, final T element) {
        System.out.print(message);
        System.out.println(element.toString());
    }
}
