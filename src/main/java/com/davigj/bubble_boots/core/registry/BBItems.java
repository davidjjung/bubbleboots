package com.davigj.bubble_boots.core.registry;

import com.davigj.bubble_boots.common.item.BubbleBootsItem;
import com.davigj.bubble_boots.core.BubbleBootsMod;
import com.davigj.bubble_boots.core.other.BBTiers;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.item.crafting.Ingredient.of;

@Mod.EventBusSubscriber(modid = BubbleBootsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBItems {
    public static final ItemSubRegistryHelper HELPER = BubbleBootsMod.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> BUBBLE_BOOTS = HELPER.createItem("bubble_boots", () -> {
        return new BubbleBootsItem(BBTiers.BUBBLE_BLOWER, (new Item.Properties()));});


    public static void buildCreativeTabContents() {
        CreativeModeTabContentsPopulator.mod(BubbleBootsMod.MOD_ID)
                .tab(CreativeModeTabs.COMBAT)
                .addItemsAfter(of(Items.TURTLE_HELMET), BUBBLE_BOOTS);
    }
}
