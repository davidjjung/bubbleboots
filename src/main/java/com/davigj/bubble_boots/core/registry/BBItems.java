package com.davigj.bubble_boots.core.registry;

import com.davigj.bubble_boots.common.item.BubbleBootsItem;
import com.davigj.bubble_boots.core.BubbleBoots;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.minecraft.world.item.crafting.Ingredient.of;

public class BBItems {
    public static final ItemSubRegistryHelper ITEMS = BubbleBoots.REGISTRY_HELPER.getItemSubHelper();

    public static final DeferredItem<BubbleBootsItem> BUBBLE_BOOTS = ITEMS.createItem("bubble_boots", () -> {
        return new BubbleBootsItem(BBTiers.BUBBLE_BLOWER, (new Item.Properties()));});

    public static void buildCreativeTabContents() {
        CreativeModeTabContentsPopulator.mod(BubbleBoots.MOD_ID)
                .tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .addItemsBefore(of(Items.FLINT_AND_STEEL), BUBBLE_BOOTS);
    }
}
