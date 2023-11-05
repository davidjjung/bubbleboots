package com.davigj.bubble_boots.core.registry;

import com.davigj.bubble_boots.common.item.BubbleBootsItem;
import com.davigj.bubble_boots.core.BBMod;
import com.davigj.bubble_boots.core.other.BBTiers;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = BBMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBItems {
    public static final ItemSubRegistryHelper HELPER = BBMod.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> BUBBLE_BOOTS = HELPER.createItem("bubble_boots", () -> {
        return new BubbleBootsItem(BBTiers.BUBBLE_BLOWER, EquipmentSlot.FEET,
                (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT));});

}
