package com.gildedrose.strategy;

import com.gildedrose.Item;

public class BackstageStrategy implements ItemUpdateStrategy{
    @Override
    public boolean isSupportable(Item item) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item.name);
    }

    @Override
    public void update(Item item) {
        ItemUpdateStrategy.increaseQuality(item, 1);
        if(item.sellIn < 11) {
            ItemUpdateStrategy.increaseQuality(item, 1);
        }
        if(item.sellIn < 6) {
            ItemUpdateStrategy.increaseQuality(item, 1);
        }

        ItemUpdateStrategy.decreaseSellIn(item, 1);

        if(item.sellIn < 0) {
            ItemUpdateStrategy.minimizeQuality(item);
        }
    }
}
