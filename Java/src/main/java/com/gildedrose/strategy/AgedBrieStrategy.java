package com.gildedrose.strategy;

import com.gildedrose.Item;

public class AgedBrieStrategy implements ItemUpdateStrategy{
    @Override
    public boolean isSupportable(Item item) {
        return "Aged Brie".equals(item.name);
    }

    @Override
    public void update(Item item) {
        ItemUpdateStrategy.increaseQuality(item, 1);

        ItemUpdateStrategy.decreaseSellIn(item, 1);

        if(item.sellIn < 0) {
            ItemUpdateStrategy.increaseQuality(item, 1);
        }
    }
}
