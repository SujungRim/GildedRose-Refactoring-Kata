package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
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

    public void updateQuality() {
        Arrays.stream(items)
            .forEach(item -> {
                UpdateStrategy strategy = this.getStrategy(item);
                strategy.update(item);
            });
    }

    public interface UpdateStrategy {
        default void update(Item item) {
            preProcessQuality(item);
            processSellIn(item);
            postProcessQuality(item);
        }
        void preProcessQuality(Item item);
        void processSellIn(Item item);
        void postProcessQuality(Item item);
    }

    public UpdateStrategy getStrategy(Item item) {
        switch (item.name) {
            case "Sulfuras, Hand of Ragnaros" : return new UpdateStrategy() {
                @Override
                public void preProcessQuality(Item item) {}
                @Override
                public void processSellIn(Item item) {}
                @Override
                public void postProcessQuality(Item item) {}
            };
            case "Aged Brie": return new UpdateStrategy() {
                @Override
                public void preProcessQuality(Item item) {
                    item.increaseQuality();
                }
                @Override
                public void processSellIn(Item item) {
                    item.decreaseSellIn();
                }
                @Override
                public void postProcessQuality(Item item) {
                    if(item.sellIn < 0) {
                        item.increaseQuality();
                    }
                }
            };
            case "Backstage passes to a TAFKAL80ETC concert": return new UpdateStrategy() {
                @Override
                public void preProcessQuality(Item item) {
                    item.increaseQuality();
                    if(item.sellIn < 11) {
                        item.increaseQuality();
                    }
                    if(item.sellIn < 6) {
                        item.increaseQuality();
                    }
                }
                @Override
                public void processSellIn(Item item) {
                    item.decreaseSellIn();
                }
                @Override
                public void postProcessQuality(Item item) {
                    if(item.sellIn < 0) {
                        item.resetQuality();
                    }
                }
            };
            case "Conjured Mana Cake": return new UpdateStrategy() {
                @Override
                public void preProcessQuality(Item item) {
                    item.decreaseQuality();
                    item.decreaseQuality();
                }
                @Override
                public void processSellIn(Item item) {
                    item.decreaseSellIn();
                }
                @Override
                public void postProcessQuality(Item item) {
                    if(item.sellIn < 0) {
                        item.decreaseQuality();
                        item.decreaseQuality();
                    }
                }
            };
            default: return new UpdateStrategy() {
                @Override
                public void preProcessQuality(Item item) {
                    item.decreaseQuality();
                }
                @Override
                public void processSellIn(Item item) {
                    item.decreaseSellIn();
                }
                @Override
                public void postProcessQuality(Item item) {
                    if(item.sellIn < 0) {
                        item.decreaseQuality();
                    }
                }
            };
        }
    }

}
