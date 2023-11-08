package com.davigj.bubble_boots.core.other;

import com.teamabnormals.blueprint.core.api.BlueprintArmorMaterial;
import net.mehvahdjukaar.supplementaries.reg.ModRegistry;
import net.mehvahdjukaar.supplementaries.reg.ModSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class BBTiers {
    public static final BlueprintArmorMaterial BUBBLE_BLOWER;

    public BBTiers() {}

    static {
        BUBBLE_BLOWER = new BlueprintArmorMaterial(new ResourceLocation(
                "bubble_boots", "bubble_blower"), 0, new int[]{1, 0, 0, 0}, 0,
                ModSounds.BUBBLE_POP, 0.0F, 0.0F, () -> {
            return Ingredient.of(new ItemLike[]{(ItemLike) ModRegistry.SOAP.get()});
        });
    }
}
