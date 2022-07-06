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
}
