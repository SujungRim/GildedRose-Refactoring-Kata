package com.gildedrose.strategy;

import com.gildedrose.Item;

import java.util.Arrays;
import java.util.List;

public class ItemStrategyFactory {

    private final List<ItemUpdateStrategy> updateStrategies;

    private ItemStrategyFactory(List<ItemUpdateStrategy> updateStrategies) {
        this.updateStrategies = updateStrategies;
    }

    public static ItemStrategyFactory create() {
        List<ItemUpdateStrategy> updateStrategies = Arrays.asList(
            new SulfurasStrategy(),
            new AgedBrieStrategy(),
            new BackstageStrategy(),
            new ConjuredStrategy(),
            new BasicStrategy()
        );
        return new ItemStrategyFactory(updateStrategies);
    }

    public ItemUpdateStrategy getUpdateStrategy(Item item) {
        return updateStrategies.stream()
            .filter(itemUpdateStrategy -> itemUpdateStrategy.isSupportable(item))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid Item"));
    }
}
