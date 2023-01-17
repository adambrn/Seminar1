package FamilyTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Persona> family = new ArrayList<>();
        Persona john = new Persona("John", Gender.MALE, null, null);
        Persona marta = new Persona("Marta", Gender.FEMALE, null, null);
        john.partner = marta;
        marta.partner = john;
        Persona piter = new Persona("Piter", Gender.MALE, john, marta);
        Persona mary = new Persona("Mary", Gender.FEMALE, john, marta);
        Persona alex = new Persona("Alex", Gender.MALE, mary, null);
        john.addChild(piter);
        john.addChild(mary);
        marta.addChild(piter);
        marta.addChild(mary);
        mary.addChild(alex);
        Persona jack = new Persona("Jack", Gender.MALE, null, null);
        mary.partner = jack;
        jack.partner = mary;
        jack.addChild(alex);
        family.add(john);
        family.add(marta);
        family.add(piter);
        family.add(mary);
        family.add(alex);
        family.add(jack);

        final String helpMessage = """
                Введите 0, это меню.
                Введите 1, вывести список всех людей.
                Введите 2, вывести дерево для определенного человека.
                Введите 3, чтобы завершить программу.""";

        int inputOption;
        boolean shouldContinue = true;

        prettyPrint(helpMessage);

        Scanner scanner = new Scanner(System.in);

        while (shouldContinue) {
            System.out.println("Введите цифру");
            do {
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                inputOption = scanner.nextInt();
            } while (!Input.isValidOption(inputOption));

            scanner.nextLine();

            switch (inputOption) {
                case 0:
                    prettyPrint(helpMessage);

                    break;
                case 1:
                    String message = "";
                    for (Persona itemPersona : family) {
                        message += itemPersona.name + "\n";
                    }
                    prettyPrint(message);
                    break;
                case 2:
                    String rootName;
                    Persona root = null;
                    System.out.println("Введите имя");
                    do {
                        rootName = scanner.nextLine();
                    } while (!Input.isValidOption(inputOption));
                    for (Persona itemPersona : family) {
                        String name = itemPersona.name;
                        if (name.equalsIgnoreCase(rootName)) {
                            root = itemPersona;
                        }

                    }
                    if (root == null) {
                        prettyPrint("В семье нет человека с таким именем");
                    } else {
                        System.out.println();
                        System.out.println("====================");
                        ViewTree.view(root, 0);
                        System.out.println("====================");
                        System.out.println();
                    }
                    break;
                case 3:

                    prettyPrint("Выходим!");

                    shouldContinue = false;
                    break;
            }
        }

        scanner.close();
        System.exit(0);

    }

    private static void prettyPrint(String message) {
        System.out.println();
        System.out.println("====================");
        System.out.println(message);
        System.out.println("====================");
        System.out.println();
    }

}
