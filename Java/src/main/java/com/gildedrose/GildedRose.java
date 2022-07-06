package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;


    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
            .forEach(this::updateQuality);
    }

    private void updateQuality(Item item) {
        ItemUpdateStrategy strategy = getUpdateStrategy(item);
        strategy.update(item);
    }

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
                ItemUpdateStrategy.decreaseQuality(item);
                ItemUpdateStrategy.decreaseQuality(item);

                ItemUpdateStrategy.decreaseSellIn(item);

                if(item.sellIn < 0) {
                    ItemUpdateStrategy.decreaseQuality(item);
                    ItemUpdateStrategy.decreaseQuality(item);
                }
            };
            default: return basic -> {
                ItemUpdateStrategy.decreaseQuality(item);

                ItemUpdateStrategy.decreaseSellIn(item);

                if(item.sellIn < 0) {
                    ItemUpdateStrategy.decreaseQuality(item);
                }
            };
        }
    }


//    public void updateQuality() {
//        for (int i = 0; i < items.length; i++) {
//
//            // 5. 전설의 아이템 -> 제외. 어떤 처리도 하지 않음
//            if ("Sulfuras, Hand of Ragnaros".equals(items[i].name)) {
//                continue;
//            }
//
//            // 3. 오래된 브리치즈
//            // 시간이 지날수록 Quality 오름
//            if ("Aged Brie".equals(items[i].name)) {
//                items[i].increaseQuality();
//            } else if("Backstage passes to a TAFKAL80ETC concert".equals(items[i].name)) {
//                // 6. 백스테이지 입장권
//                // 시간이 지날수록 Quality 오름
//                items[i].increaseQuality();
//                // -> 추가 증가
//                // 10일 전까지 2씩 증가
//                if(items[i].sellIn < 11) {
//                    items[i].increaseQuality();
//                }
//                // 5일 전까지 3씩 증가
//                if(items[i].sellIn < 6) {
//                    items[i].increaseQuality();
//                }
//
//            } else if("Conjured Mana Cake".equals(items[i].name)) {
//                items[i].decreaseQuality();
//                items[i].decreaseQuality();
//            } else {
//                // 5. 나머지 Quality 감소
//                items[i].decreaseQuality();
//            }
//
//
//            // 5. 전설의 아이템은 판매될 필요도 없고 Quality도 떨어지지 않는다. -> 나머지는 다 떨어트림
//            items[i].decreaseSellIn();
//
//            // 1. 판매하는 나머지 일수가 없어지면 Quality 값은 2배로 떨어진다. -> 한번씩 더 수행
//            if(items[i].sellIn < 0) {
//                if ("Aged Brie".equals(items[i].name)) {
//                    // 3. 오래된 브리치즈
//                    // 시간이 지날수록 Quality 오름
//                    items[i].increaseQuality();
//                } else if ("Backstage passes to a TAFKAL80ETC concert".equals(items[i].name)) {
//                    // 6. 백스테이지 입장권
//                    // 콘서트 종료 후 (= SellIn이 0이하일 때) 0으로 떨어짐
//                    items[i].resetQuality();
//                } else if ("Conjured Mana Cake".equals(items[i].name)) {
//                    items[i].decreaseQuality();
//                    items[i].decreaseQuality();
//                } else {
//                    // 5. 전설의 아이템 제외 Quality 감소
//                    items[i].decreaseQuality();
//                }
//            }
//        }
//    }

}
