package com.gildedrose.strategy;

import com.gildedrose.Item;

public interface ItemUpdateStrategy {

    int UPPER_LIMIT_OF_QUALITY = 50;
    int LOWER_LIMIT_OF_QUALITY = 0;
    int ZERO = 0;

    boolean isSupportable(Item item);
    void update(Item item);

    static void increaseQuality(Item item, int value) {
        item.quality = item.quality + value;
        normalizeQuality(item);
    }

    static void decreaseQuality(Item item, int value) {
        item.quality = item.quality - value;
        normalizeQuality(item);
    }

    static void normalizeQuality(Item item) {
        if (item.quality > UPPER_LIMIT_OF_QUALITY) {
            item.quality = UPPER_LIMIT_OF_QUALITY;
        }
        if (item.quality < LOWER_LIMIT_OF_QUALITY) {
            item.quality = LOWER_LIMIT_OF_QUALITY;
        }
    }

    static void minimizeQuality(Item item) {
        item.quality = ZERO;
    }

    static void decreaseSellIn(Item item, int value) {
        item.sellIn = item.sellIn - value;
    }
}
