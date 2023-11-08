package com.davigj.bubble_boots.core.registry;

import com.davigj.bubble_boots.core.BubbleBootsMod;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = BubbleBootsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBSounds {
    public static final SoundSubRegistryHelper HELPER = BubbleBootsMod.REGISTRY_HELPER.getSoundSubHelper();

    public static final RegistryObject<SoundEvent> BUBBLES = HELPER.createSoundEvent("item.bubble_boots.bubbles");

}
