package com.example.cardgamemarket.model;

import java.util.List;

public class PokemonCard {
    private String id;
    private String name;
    private String supertype;
    private List<String> subtypes;
    private String hp;
    private List<String> types;
    private List<String> evolvesTo;
    private List<String> rules;
    private List<Attack> attacks;
    private List<Weakness> weaknesses;
    private List<String> retreatCost;
    private int convertedRetreatCost;
    private SetInfo set;
    private String number;
    private String artist;
    private String rarity;
    private List<Integer> nationalPokedexNumbers;
    private Legalities legalities;
    private Images images;
    private TcgPlayer tcgplayer;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHp() {
        return hp;
    }

    public String getTypes() {
        return supertype;
    }

    public String getSmallImages() {
        return images.small;
    }

    public static class Attack {
    private String name;
    private List<String> cost;
    private int convertedEnergyCost;
    private String damage;
    private String text;
}

public static class Weakness {
    private String type;
    private String value;
}

public static class SetInfo {
    private String id;
    private String name;
    private String series;
    private int printedTotal;
    private int total;
    private Legalities legalities;
    private String ptcgoCode;
    private String releaseDate;
    private String updatedAt;
    private Images images;
}

    public static class Legalities {
        private String unlimited;
        private String expanded;
    }
    public static class Images {
        private String small;
        private String large;
    }

    public static class TcgPlayer {
        private String url;
        private String updatedAt;
        private Prices prices;
    }

    public static class Prices {
        private Holofoil holofoil;
    }

    public class Holofoil {
        private double low;
        private double mid;
        private double high;
        private double market;
        private double directLow;
    }
}





