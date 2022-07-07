package com.gildedrose;

import com.gildedrose.strategy.ItemStrategyFactory;
import com.gildedrose.strategy.ItemUpdateStrategy;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ItemUpdateStrategyTest {

    @Nested
    class AtomicOperationTest {
        @DisplayName("판매기한 감소 - 정상 감소")
        @Test
        void When_DecreaseSellIn_Then_SellInDecreaseByValue() {
            // given
            int sellIn = 1;
            int quality = 1;
            int value = 1;
            Item item = new Item("none", sellIn, quality);
            // when
            ItemUpdateStrategy.decreaseSellIn(item, value);
            // then
            assertEquals(sellIn - value, item.sellIn);
        }

        @DisplayName("퀄리티 감소 - 정상 감소")
        @Test
        void When_DecreaseQuality_Then_QualityDecreaseByValue() {
            // given
            int sellIn = 1;
            int quality = 1;
            int value = 1;
            Item item = new Item("none", sellIn, quality);
            // when
            ItemUpdateStrategy.decreaseQuality(item, value);
            // then
            assertEquals(quality - value, item.quality);
        }

        @DisplayName("퀄리티 감소 - 퀄리티는 음수가 될 수 없다. 퀄리티 값 유지")
        @Test
        void When_DecreaseQuality_Then_QualityCanNotBeNegative() {
            // given
            int sellIn = 1;
            int quality = 0;
            int value = 1;
            Item item = new Item("none", sellIn, quality);
            // when
            ItemUpdateStrategy.decreaseQuality(item, value);
            // then
            assertEquals(quality, item.quality);
        }

        @DisplayName("퀄리티 최소화 - 0")
        @Test
        void When_ResetQuality_Then_QualityTo0() {
            // given
            int sellIn = 1;
            int quality = 10;
            Item item = new Item("none", sellIn, quality);
            // when
            ItemUpdateStrategy.minimizeQuality(item);
            // then
            assertEquals(0, item.quality);
        }

        @DisplayName("퀄리티 증가 - 정상 증가")
        @Test
        void When_IncreaseQuality_Then_QualityIncreaseBy1() {
            // given
            int sellIn = 1;
            int quality = 0;
            int value = 1;
            Item item = new Item("none", sellIn, quality);
            // when
            ItemUpdateStrategy.increaseQuality(item, value);
            // then
            assertEquals(quality + value, item.quality);
        }

        @DisplayName("퀄리티 증가 - 퀄리티는 50을 초과할 수 없다. 퀄리티값 유지")
        @Test
        void When_IncreaseQuality_Then_QualityCanNotBeExceeded50() {
            // given
            int sellIn = 1;
            int quality = 50;
            int value = 1;
            Item item = new Item("none", sellIn, quality);
            // when
            ItemUpdateStrategy.increaseQuality(item, value);
            // then
            assertEquals(quality, item.quality);
        }
    }

    @Nested
    class UpdateOperationTest {

        ItemStrategyFactory itemStrategyFactory;

        @BeforeEach
        void setUp() {
            itemStrategyFactory = ItemStrategyFactory.create();
        }

        @DisplayName("Sulfuras 업데이트 - 판매기한, 퀄리티 변동없음")
        @Test
        void Given_Sulfuras_When_UpdateQuality_Then_QualityAndSellInBeingTheSame() {
            // given
            int sellIn = 0;
            int quality = 80;
            Item item = new Item("Sulfuras, Hand of Ragnaros", sellIn, quality); //
            // when
            ItemUpdateStrategy strategy = itemStrategyFactory.getUpdateStrategy(item);
            strategy.update(item);
            // then
            assertEquals(sellIn, item.sellIn);
            assertEquals(quality, item.quality);
        }

        @DisplayName("기본 업데이트 - 판매기한 1 감소, 퀄리티 1 감소")
        @Test
        void Given_OtherItem_When_UpdateQuality_Then_QualityDecreaseBy1() {
            // given
            int sellIn = 10;
            int quality = 20;
            Item item = new Item("+5 Dexterity Vest", sellIn, quality); //
            // when
            ItemUpdateStrategy strategy = itemStrategyFactory.getUpdateStrategy(item);
            strategy.update(item);
            // then
            assertEquals(sellIn - 1, item.sellIn);
            assertEquals(quality - 1, item.quality);
        }

        @DisplayName("기본 업데이트 - 판매기한 종료 시, 판매기한 1 감소, 퀄리티 2 감소")
        @Test
        void Given_OtherItem_When_UpdateQuality_Then_QualityDecreaseTwice_SellInUnder0() {
            // given
            int sellIn = -1;
            int quality = 20;
            Item item = new Item("+5 Dexterity Vest", sellIn, quality); //
            // when
            ItemUpdateStrategy strategy = itemStrategyFactory.getUpdateStrategy(item);
            strategy.update(item);
            // then
            assertEquals(sellIn - 1, item.sellIn);
            assertEquals(quality - 2, item.quality);
        }

        @DisplayName("Aged Brie 업데이트 - 판매기한 1 감소, 퀄리티 1 증가")
        @Test
        void Given_AgedBrie_When_UpdateQuality_Then_QualityIncreaseBy1() {
            // given
            int sellIn = 2;
            int quality = 0;
            Item item = new Item("Aged Brie", sellIn, quality); //
            // when
            ItemUpdateStrategy strategy = itemStrategyFactory.getUpdateStrategy(item);
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
            ItemUpdateStrategy strategy = itemStrategyFactory.getUpdateStrategy(item);
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
            ItemUpdateStrategy strategy = itemStrategyFactory.getUpdateStrategy(item);
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
            ItemUpdateStrategy strategy = itemStrategyFactory.getUpdateStrategy(item);
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
            ItemUpdateStrategy strategy = itemStrategyFactory.getUpdateStrategy(item);
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
            ItemUpdateStrategy strategy = itemStrategyFactory.getUpdateStrategy(item);
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
            ItemUpdateStrategy strategy = itemStrategyFactory.getUpdateStrategy(item);
            strategy.update(item);
            // then
            assertEquals(sellIn - 1, item.sellIn);
            assertEquals(quality - 4, item.quality);
        }
    }

}
