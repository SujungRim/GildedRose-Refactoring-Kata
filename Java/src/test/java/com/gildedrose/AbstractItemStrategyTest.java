package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractItemStrategyTest {

    @DisplayName("판매기한 감소 - 1 정상 감소")
    @Test
    void When_DecreaseSellIn_Then_SellInDecreaseBy1() {
        // given
        Item item = new Item("none", 1, 1);
        // when
        AbstractItemStrategy.decreaseSellIn(item);
        // then
        assertEquals(0, item.sellIn);
    }

    @DisplayName("퀄리티 감소 - 1 정상 감소")
    @Test
    void When_DecreaseQuality_Then_QualityDecreaseBy1() {
        // given
        Item item = new Item("none", 1, 1);
        // when
        AbstractItemStrategy.decreaseQuality(item);
        // then
        assertEquals(0, item.quality);
    }

    @DisplayName("퀄리티 감소 - 퀄리티는 음수가 될 수 없다. 퀄리티 값 유지")
    @Test
    void When_DecreaseQuality_Then_QualityCanNotBeNegative() {
        // given
        Item item = new Item("none", 1, 0);
        // when
        AbstractItemStrategy.decreaseQuality(item);
        // then
        assertEquals(0, item.quality);
    }

    @DisplayName("퀄리티 초기화 - 0")
    @Test
    void When_ResetQuality_Then_QualityTo0() {
        // given
        Item item = new Item("none", 1, 10);
        // when
        AbstractItemStrategy.resetQuality(item);
        // then
        assertEquals(0, item.quality);
    }

    @DisplayName("퀄리티 증가 - 1 정상 증가")
    @Test
    void When_IncreaseQuality_Then_QualityIncreaseBy1() {
        // given
        Item item = new Item("none", 1, 0);
        // when
        AbstractItemStrategy.increaseQuality(item);
        // then
        assertEquals(1, item.quality);
    }

    @DisplayName("퀄리티 증가 - 퀄리티는 50을 초과할 수 없다. 퀄리티값 유지")
    @Test
    void When_IncreaseQuality_Then_QualityCanNotBeExceeded50() {
        // given
        Item item = new Item("none", 1, 50);
        // when
        AbstractItemStrategy.increaseQuality(item);
        // then
        assertEquals(50, item.quality);
    }
}
