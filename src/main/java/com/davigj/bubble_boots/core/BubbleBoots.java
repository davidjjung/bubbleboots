package com.davigj.bubble_boots.core;

import com.davigj.bubble_boots.core.other.BBClientCompat;
import com.davigj.bubble_boots.core.registry.BBItems;
import com.davigj.bubble_boots.core.registry.BBSounds;
import com.davigj.bubble_boots.core.registry.BBTiers;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(BubbleBoots.MOD_ID)
public class BubbleBoots {
    public static final String MOD_ID = "bubble_boots";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

    public BubbleBoots(IEventBus bus, ModContainer container) {
        BBItems.ITEMS.register(bus);
        BBSounds.SOUNDS.register(bus);
        BBTiers.ARMOR_MATERIALS.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::dataSetup);

        container.registerConfig(ModConfig.Type.COMMON, BBConfig.COMMON_SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, BBConfig.CLIENT_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
        });
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            event.enqueueWork(BBClientCompat::register);
        });
    }

    private void dataSetup(GatherDataEvent event) {

    }

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}