package com.davigj.bubble_boots.core.registry;

import com.davigj.bubble_boots.core.BubbleBoots;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BBSounds {
    public static final SoundSubRegistryHelper SOUNDS = BubbleBoots.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> BUBBLES = SOUNDS.createSoundEvent("item.bubble_boots.bubbles");
}
