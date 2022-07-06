package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Disabled
    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

    @Test
    void Given_AllItem_When_UpdateQuality_Then_SellInDecreaseBy1() {
        // given
        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item("Conjured Mana Cake", 3, 6)
        };
        // when
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // then
        Assertions.assertEquals(9, items[0].sellIn);
        Assertions.assertEquals(1, items[1].sellIn);
        Assertions.assertEquals(4, items[2].sellIn);
        Assertions.assertEquals(14, items[3].sellIn);
        Assertions.assertEquals(9, items[4].sellIn);
        Assertions.assertEquals(4, items[5].sellIn);
        Assertions.assertEquals(2, items[6].sellIn);
    }


    @Test
    void Given_AgedBrie_When_UpdateQuality_Then_QualityIncreaseBy1() {
        // given
        Item[] items = new Item[]{
            new Item("Aged Brie", 2, 0), //
        };
        // when
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // then
        Assertions.assertEquals(1, items[0].quality);
    }

    @Test
    void Given_Backstagepasses_When_UpdateQuality_Then_QualityIncreaseBy1() {
        // given
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
        };
        // when
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // then
        Assertions.assertEquals(21, items[0].quality);
    }

    @Test
    void Given_Backstagepasses_When_UpdateQuality_Then_QualityIncreaseBy2Before10() {
        // given
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20),
        };
        // when
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // then
        Assertions.assertEquals(22, items[0].quality);
    }

    @Test
    void Given_Backstagepasses_When_UpdateQuality_Then_QualityIncreaseBy2Before10_Limit50() {
        // given
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
        };
        // when
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // then
        Assertions.assertEquals(50, items[0].quality);
    }

    @Test
    void Given_Backstagepasses_When_UpdateQuality_Then_QualityIncreaseBy3Before5() {
        // given
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20),
        };
        // when
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // then
        Assertions.assertEquals(23, items[0].quality);
    }

    @Test
    void Given_Backstagepasses_When_UpdateQuality_Then_QualityIncreaseBy3Before5_Limit50() {
        // given
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
        };
        // when
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // then
        Assertions.assertEquals(50, items[0].quality);
    }

    @Test
    void Given_Backstagepasses_When_UpdateQuality_Then_QualityTo0() {
        // given
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 49),
        };
        // when
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // then
        Assertions.assertEquals(0, items[0].quality);
    }

    @Test
    void Given_Sulfuras_When_UpdateQuality_Then_QualityAndSellInBeingTheSame() {
        // given
        Item[] items = new Item[]{
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
        };
        // when
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // then
        Assertions.assertEquals(80, items[0].quality);
        Assertions.assertEquals(0, items[0].sellIn);

        Assertions.assertEquals(80, items[1].quality);
        Assertions.assertEquals(-1, items[1].sellIn);
    }

    @Test
    void Given_OtherItem_When_UpdateQuality_Then_QualityDecreaseBy1() {
        // given
        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 20), //
        };
        // when
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // then
        Assertions.assertEquals(19, items[0].quality);
    }

    @Test
    void Given_OtherItem_When_UpdateQuality_Then_QualityDecreaseTwice_SellInUnder0() {
        // given
        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", -1, 20), //
        };
        // when
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // then
        Assertions.assertEquals(18, items[0].quality);
    }

    @Test
    void Given_Conjured_When_UpdateQuality_Then_QualityDecreaseTwice() {
        // given
        Item[] items = new Item[]{
            new Item("Conjured Mana Cake", 3, 6)
        };
        // when
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // then
        Assertions.assertEquals(4, items[0].quality);
    }

    @Test
    void Given_Conjured_When_UpdateQuality_Then_QualityDecreaseQuadruple_SellInUnder0() {
        // given
        Item[] items = new Item[]{
            new Item("Conjured Mana Cake", -1, 6)
        };
        // when
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // then
        Assertions.assertEquals(2, items[0].quality);
    }
}
