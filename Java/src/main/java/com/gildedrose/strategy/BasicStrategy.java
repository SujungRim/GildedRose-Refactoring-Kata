package com.gildedrose.strategy;

import com.gildedrose.Item;

public class BasicStrategy implements ItemUpdateStrategy{
    @Override
    public boolean isSupportable(Item item) {
        return true;
    }

    @Override
    public void update(Item item) {
        ItemUpdateStrategy.decreaseQuality(item,1);

        ItemUpdateStrategy.decreaseSellIn(item, 1);

        if(item.sellIn < 0) {
            ItemUpdateStrategy.decreaseQuality(item, 1);
        }
    }
}
