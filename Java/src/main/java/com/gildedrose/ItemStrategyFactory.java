package com.gildedrose;

public class ItemStrategyFactory {

    public static ItemUpdateStrategy getUpdateStrategy(Item item) {
        switch (item.name) {
            case "Sulfuras, Hand of Ragnaros" :
                return sulfuras -> {};

            case "Aged Brie":
                return agedBrie -> {
                    ItemUpdateStrategy.increaseQuality(agedBrie);

                    ItemUpdateStrategy.decreaseSellIn(agedBrie);

                    if(agedBrie.sellIn < 0) {
                        ItemUpdateStrategy.increaseQuality(agedBrie);
                    }
                };

            case "Backstage passes to a TAFKAL80ETC concert": return backstage -> {
                ItemUpdateStrategy.increaseQuality(backstage);
                if(backstage.sellIn < 11) {
                    ItemUpdateStrategy.increaseQuality(backstage);
                }
                if(backstage.sellIn < 6) {
                    ItemUpdateStrategy.increaseQuality(backstage);
                }

                ItemUpdateStrategy.decreaseSellIn(backstage);

                if(backstage.sellIn < 0) {
                    ItemUpdateStrategy.resetQuality(backstage);
                }
            };

            case "Conjured Mana Cake": return conjured -> {
                ItemUpdateStrategy.decreaseQuality(conjured);
                ItemUpdateStrategy.decreaseQuality(conjured);

                ItemUpdateStrategy.decreaseSellIn(conjured);

                if(conjured.sellIn < 0) {
                    ItemUpdateStrategy.decreaseQuality(conjured);
                    ItemUpdateStrategy.decreaseQuality(conjured);
                }
            };

            default: return basic -> {
                ItemUpdateStrategy.decreaseQuality(basic);

                ItemUpdateStrategy.decreaseSellIn(basic);

                if(basic.sellIn < 0) {
                    ItemUpdateStrategy.decreaseQuality(basic);
                }
            };
        }
    }




}
