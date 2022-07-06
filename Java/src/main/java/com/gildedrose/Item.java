package com.gildedrose;

public class Item {

    public static final int UPPER_LIMIT_OF_QUALITY = 50;
    public static final int LOWER_LIMIT_OF_QUALITY = 0;
    public static final int ONE_UNIT = 1;
    public static final int ZERO = 0;

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public void increaseQuality() {
        if (this.quality < UPPER_LIMIT_OF_QUALITY) {
            this.quality = this.quality + ONE_UNIT;
        }
    }

    public void decreaseQuality() {
        if (this.quality > LOWER_LIMIT_OF_QUALITY) {
            this.quality = this.quality - ONE_UNIT;
        }
    }

    public void resetQuality() {
        this.quality = ZERO;
    }

    public void decreaseSellIn() {
        this.sellIn = this.sellIn - ONE_UNIT;
    }
}
