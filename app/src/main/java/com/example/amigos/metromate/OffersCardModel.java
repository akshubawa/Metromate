package com.example.amigos.metromate;

public class OffersCardModel {
    private String offer;
    private String heading;

    public OffersCardModel() {}

    public OffersCardModel(String heading, String offer) {
        this.offer = offer;
        this.heading = heading;
    }

    public String getOffer() {
        return offer;
    }

    public String getHeading() {
        return heading;
    }
}
