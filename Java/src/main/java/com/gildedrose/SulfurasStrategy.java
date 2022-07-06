package com.gildedrose;

public class SulfurasStrategy extends AbstractItemStrategy {

    @Override
    boolean isSupportable(Item item) {
        return "Sulfuras, Hand of Ragnaros".equals(item);
    }

    @Override
    void preProcessQuality(Item item) {}

    @Override
    void processSellIn(Item item) {}

    @Override
    void postProcessQuality(Item item) {}
}
