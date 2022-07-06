package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemUpdateStrategyTest {

    /**
     * default method test : basic operation
     */

    @DisplayName("판매기한 감소 - 1 정상 감소")
    @Test
    void When_DecreaseSellIn_Then_SellInDecreaseBy1() {
        // given
        Item item = new Item("none", 1, 1);
        // when
        ItemUpdateStrategy.decreaseSellIn(item);
        // then
        assertEquals(0, item.sellIn);
    }

    @DisplayName("퀄리티 감소 - 1 정상 감소")
    @Test
    void When_DecreaseQuality_Then_QualityDecreaseBy1() {
        // given
        Item item = new Item("none", 1, 1);
        // when
        ItemUpdateStrategy.decreaseQuality(item);
        // then
        assertEquals(0, item.quality);
    }

    @DisplayName("퀄리티 감소 - 퀄리티는 음수가 될 수 없다. 퀄리티 값 유지")
    @Test
    void When_DecreaseQuality_Then_QualityCanNotBeNegative() {
        // given
        Item item = new Item("none", 1, 0);
        // when
        ItemUpdateStrategy.decreaseQuality(item);
        // then
        assertEquals(0, item.quality);
    }

    @DisplayName("퀄리티 초기화 - 0")
    @Test
    void When_ResetQuality_Then_QualityTo0() {
        // given
        Item item = new Item("none", 1, 10);
        // when
        ItemUpdateStrategy.resetQuality(item);
        // then
        assertEquals(0, item.quality);
    }

    @DisplayName("퀄리티 증가 - 1 정상 증가")
    @Test
    void When_IncreaseQuality_Then_QualityIncreaseBy1() {
        // given
        Item item = new Item("none", 1, 0);
        // when
        ItemUpdateStrategy.increaseQuality(item);
        // then
        assertEquals(1, item.quality);
    }

    @DisplayName("퀄리티 증가 - 퀄리티는 50을 초과할 수 없다. 퀄리티값 유지")
    @Test
    void When_IncreaseQuality_Then_QualityCanNotBeExceeded50() {
        // given
        Item item = new Item("none", 1, 50);
        // when
        ItemUpdateStrategy.increaseQuality(item);
        // then
        assertEquals(50, item.quality);
    }


    /**
     * update strategy test by item
     */

    @DisplayName("Sulfuras 업데이트 - 판매기한, 퀄리티 변동없음")
    @Test
    void Given_Sulfuras_When_UpdateQuality_Then_QualityAndSellInBeingTheSame() {
        // given
        int sellIn = 0;
        int quality = 80;
        Item item = new Item("Sulfuras, Hand of Ragnaros", sellIn, quality); //
        // when
        ItemUpdateStrategy strategy = ItemStrategyFactory.getUpdateStrategy(item);
        strategy.update(item);
        // then
        assertEquals(sellIn, item.sellIn);
        assertEquals(quality, item.quality);
    }

    @DisplayName("Aged Brie 업데이트 - 판매기한 1 감소, 퀄리티 1 증가")
    @Test
    void Given_AgedBrie_When_UpdateQuality_Then_QualityIncreaseBy1() {
        // given
        int sellIn = 2;
        int quality = 0;
        Item item = new Item("Aged Brie", sellIn, quality); //
        // when
        ItemUpdateStrategy strategy = ItemStrategyFactory.getUpdateStrategy(item);
        strategy.update(item);
        // then
        assertEquals(sellIn - 1, item.sellIn);
        assertEquals(quality + 1, item.quality);
    }

    @DisplayName("Backstage 업데이트 - 판매기한 10일 초과 시, 판매기한 1 감소, 퀄리티 1 증가")
    @Test
    void Given_Backstagepasses_When_UpdateQuality_Then_QualityIncreaseBy1() {
        // given
        int sellIn = 15;
        int quality = 20;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality); //
        // when
        ItemUpdateStrategy strategy = ItemStrategyFactory.getUpdateStrategy(item);
        strategy.update(item);
        // then
        assertEquals(sellIn - 1, item.sellIn);
        assertEquals(quality + 1, item.quality);
    }

    @DisplayName("Backstage 업데이트 - 판매기한 10일 이하 시, 판매기한 1 감소, 퀄리티 2 증가")
    @Test
    void Given_Backstagepasses_When_UpdateQuality_Then_QualityIncreaseBy2_SellInLessThan10() {
        // given
        int sellIn = 10;
        int quality = 20;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality); //
        // when
        ItemUpdateStrategy strategy = ItemStrategyFactory.getUpdateStrategy(item);
        strategy.update(item);
        // then
        assertEquals(sellIn - 1, item.sellIn);
        assertEquals(quality + 2, item.quality);
    }

    @DisplayName("Backstage 업데이트 - 판매기한 5일 이하 시, 판매기한 1 감소, 퀄리티 3 증가")
    @Test
    void Given_Backstagepasses_When_UpdateQuality_Then_QualityIncreaseBy3_SellInLessThan5() {
        // given
        int sellIn = 5;
        int quality = 20;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality); //
        // when
        ItemUpdateStrategy strategy = ItemStrategyFactory.getUpdateStrategy(item);
        strategy.update(item);
        // then
        assertEquals(sellIn - 1, item.sellIn);
        assertEquals(quality + 3, item.quality);
    }

    @DisplayName("Backstage 업데이트 - 판매기한 종료 시, 1 감소, 퀄리티 0")
    @Test
    void Given_Backstagepasses_When_UpdateQuality_Then_QualityTo0() {
        // given
        int sellIn = 0;
        int quality = 20;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality); //
        // when
        ItemUpdateStrategy strategy = ItemStrategyFactory.getUpdateStrategy(item);
        strategy.update(item);
        // then
        assertEquals(sellIn - 1, item.sellIn);
        assertEquals(0, item.quality);
    }

    @DisplayName("Conjured 업데이트 - 판매기한 1 감소, 퀄리티 2 감소")
    @Test
    void Given_Conjured_When_UpdateQuality_Then_QualityDecreaseTwice() {
        // given
        int sellIn = 3;
        int quality = 6;
        Item item = new Item("Conjured Mana Cake", sellIn, quality); //
        // when
        ItemUpdateStrategy strategy = ItemStrategyFactory.getUpdateStrategy(item);
        strategy.update(item);
        // then
        assertEquals(sellIn - 1, item.sellIn);
        assertEquals(quality - 2, item.quality);
    }

    @DisplayName("Conjured 업데이트 - 판매기한 종료 시, 판매기한 1 감소, 퀄리티 4 감소")
    @Test
    void Given_Conjured_When_UpdateQuality_Then_QualityDecreaseQuadruple_SellInUnder0() {
        // given
        int sellIn = 0;
        int quality = 6;
        Item item = new Item("Conjured Mana Cake", sellIn, quality); //
        // when
        ItemUpdateStrategy strategy = ItemStrategyFactory.getUpdateStrategy(item);
        strategy.update(item);
        // then
        assertEquals(sellIn - 1, item.sellIn);
        assertEquals(quality - 4, item.quality);
    }

    @DisplayName("나머지 업데이트 - 판매기한 1 감소, 퀄리티 1 감소")
    @Test
    void Given_OtherItem_When_UpdateQuality_Then_QualityDecreaseBy1() {
        // given
        int sellIn = 10;
        int quality = 20;
        Item item = new Item("+5 Dexterity Vest", sellIn, quality); //
        // when
        ItemUpdateStrategy strategy = ItemStrategyFactory.getUpdateStrategy(item);
        strategy.update(item);
        // then
        assertEquals(sellIn - 1, item.sellIn);
        assertEquals(quality - 1, item.quality);
    }

    @DisplayName("나머지 업데이트 - 판매기한 종료 시, 판매기한 1 감소, 퀄리티 2 감소")
    @Test
    void Given_OtherItem_When_UpdateQuality_Then_QualityDecreaseTwice_SellInUnder0() {
        // given
        int sellIn = -1;
        int quality = 20;
        Item item = new Item("+5 Dexterity Vest", sellIn, quality); //
        // when
        ItemUpdateStrategy strategy = ItemStrategyFactory.getUpdateStrategy(item);
        strategy.update(item);
        // then
        assertEquals(sellIn - 1, item.sellIn);
        assertEquals(quality - 2, item.quality);
    }

}
