package com.gildedrose;

public abstract class AbstractItemStrategy {

    public static final int UPPER_LIMIT_OF_QUALITY = 50;
    public static final int LOWER_LIMIT_OF_QUALITY = 0;
    public static final int ONE_UNIT = 1;
    public static final int ZERO = 0;

    abstract boolean isSupportable(Item item);

    public final void update(Item item) {
        preProcessQuality(item);
        processSellIn(item);
        postProcessQuality(item);
    }

    abstract void preProcessQuality(Item item);
    abstract void processSellIn(Item item);
    abstract void postProcessQuality(Item item);

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
