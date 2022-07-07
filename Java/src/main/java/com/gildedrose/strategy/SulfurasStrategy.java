package com.gildedrose.strategy;

import com.gildedrose.Item;

public class SulfurasStrategy implements ItemUpdateStrategy{
    @Override
    public boolean isSupportable(Item item) {
        return "Sulfuras, Hand of Ragnaros".equals(item.name);
    }

    @Override
    public void update(Item item) {}
}
