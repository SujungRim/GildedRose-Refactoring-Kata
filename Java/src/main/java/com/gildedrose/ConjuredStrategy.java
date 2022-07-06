package com.gildedrose;

public class ConjuredStrategy extends AbstractItemStrategy {
    @Override
    boolean isSupportable(Item item) {
        return "Conjured Mana Cake".equals(item.name);
    }

    @Override
    void preProcessQuality(Item item) {
        decreaseQuality(item);
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
            decreaseQuality(item);
        }
    }
}
