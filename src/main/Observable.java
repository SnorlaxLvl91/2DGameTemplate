package main;

import java.util.LinkedList;

public abstract class Observable {

    protected LinkedList<Observer> observers = new LinkedList<Observer>();

    public void register(Observer observer){
        observers.add(observer);
    }

    public void notifyObservers(Object obj){
        observers.stream().forEach(e -> e.update(obj));
    }
}
