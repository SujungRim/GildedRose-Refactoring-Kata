package com.gildedrose;

public class AgedBrieStrategy extends AbstractItemStrategy{
    @Override
    boolean isSupportable(Item item) {
        return "Aged Brie".equals(item.name);
    }

    @Override
    void preProcessQuality(Item item) {
        increaseQuality(item);
    }

    @Override
    void processSellIn(Item item) {
        decreaseSellIn(item);
    }

    @Override
    void postProcessQuality(Item item) {
        if(item.sellIn < 0) {
            increaseQuality(item);
        }
    }
}
