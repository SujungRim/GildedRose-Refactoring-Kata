package com.gildedrose.strategy;

import com.gildedrose.Item;

public class ConjuredStrategy implements ItemUpdateStrategy{
    @Override
    public boolean isSupportable(Item item) {
        return "Conjured Mana Cake".equals(item.name);
    }

    @Override
    public void update(Item item) {
        ItemUpdateStrategy.decreaseQuality(item, 2);

        ItemUpdateStrategy.decreaseSellIn(item, 1);

        if(item.sellIn < 0) {
            ItemUpdateStrategy.decreaseQuality(item, 2);
        }
    }
}
