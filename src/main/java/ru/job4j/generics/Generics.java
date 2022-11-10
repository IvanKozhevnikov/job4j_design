package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        /*gen.printBoundedWildCard(first);*/
        gen.printBoundedWildCard(second);
        /*gen.printBoundedWildCard(third);*/
        System.out.println();

        /*gen.printLowerBoundedWildCard(first);*/
        gen.printLowerBoundedWildCard(second);
        /*gen.printLowerBoundedWildCard(third);*/
    }

    public void printObject(Collection<?> collection) {
        for (Iterator<?> it = collection.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(Collection<? extends Predator> predator) {
        for (Iterator<? extends Predator> it = predator.iterator(); it.hasNext();) {
            Predator next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(Collection<? super Predator> predator) {
        for (Iterator<? super Predator> it = predator.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}