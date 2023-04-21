package com.example.amigos.metromate;

public class CardModel {
    String name, line, show_interchange;
    int metro_color;

    public CardModel(String name, String line, String show_interchange,int metro_color) {
        this.name = name;
        this.line = line;
        this.metro_color = metro_color;
        this.show_interchange = show_interchange;
    }
}
