package com.gildedrose;

public class BasicStrategy extends AbstractItemStrategy {
    @Override
    boolean isSupportable(Item item) {
        return true;
    }

    @Override
    void preProcessQuality(Item item) {
        decreaseQuality(item);
    }

    @Override
    void processSellIn(Item item) {
        decreaseSellIn(item);
    }

    @Override
    void postProcessQuality(Item item) {
        if(item.sellIn < 0) {
            decreaseQuality(item);
        }
    }
}
