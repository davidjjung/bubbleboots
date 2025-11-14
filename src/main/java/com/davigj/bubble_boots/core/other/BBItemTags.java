package com.davigj.bubble_boots.core.other;

import com.davigj.bubble_boots.core.BubbleBoots;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BBItemTags {
    public static final TagKey<Item> BUBBLE_RESTORERS = itemTag("bubble_restorers");

    private static TagKey<Item> itemTag(String name) {
        return TagUtil.itemTag(BubbleBoots.MOD_ID, name);
    }
}
