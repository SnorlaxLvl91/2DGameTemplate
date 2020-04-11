package main.gui;

import main.Action;

public class Item {

    String name;
    Action onClick;

    public Item(String name, Action onClick){
        this.name = name;
        this.onClick = onClick;
    }
}
