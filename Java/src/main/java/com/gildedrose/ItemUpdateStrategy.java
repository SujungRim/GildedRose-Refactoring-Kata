package com.gildedrose;

public interface ItemUpdateStrategy {

    int UPPER_LIMIT_OF_QUALITY = 50;
    int LOWER_LIMIT_OF_QUALITY = 0;
    int ONE_UNIT = 1;
    int ZERO = 0;

    void update(Item item);

    public static void increaseQuality(Item item) {
        if (item.quality < UPPER_LIMIT_OF_QUALITY) {
            item.quality = item.quality + ONE_UNIT;
        }
    }

    public static void decreaseQuality(Item item) {
        if (item.quality > LOWER_LIMIT_OF_QUALITY) {
            item.quality = item.quality - ONE_UNIT;
        }
    }

    public static void resetQuality(Item item) {
        item.quality = ZERO;
    }

    public static void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - ONE_UNIT;
    }
}
