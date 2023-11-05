package com.davigj.bubble_boots.core.registry;

import com.davigj.bubble_boots.core.BBMod;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BBMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBBlocks {
    public static final BlockSubRegistryHelper HELPER = BBMod.REGISTRY_HELPER.getBlockSubHelper();
    //	public static final RegistryObject<Block> TEMPLATE_BLOCK = HELPER.createBlock("template_block", () -> new Block(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
}