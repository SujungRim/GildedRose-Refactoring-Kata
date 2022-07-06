package com.gildedrose;

public class BackstageStrategy extends AbstractItemStrategy{
    @Override
    boolean isSupportable(Item item) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item.name);
    }

    @Override
    void preProcessQuality(Item item) {
        increaseQuality(item);
        if(item.sellIn < 11) {
            increaseQuality(item);
        }
        if(item.sellIn < 6) {
            increaseQuality(item);
        }
    }

    @Override
    void processSellIn(Item item) {
        decreaseSellIn(item);
    }

    @Override
    void postProcessQuality(Item item) {
        if(item.sellIn < 0) {
            resetQuality(item);
        }
    }
}
