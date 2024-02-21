package com.davigj.bubble_boots.core.mixin;

import com.davigj.bubble_boots.core.registry.BBItems;
import net.mehvahdjukaar.supplementaries.common.block.blocks.BubbleBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BubbleBlock.class)
public class BubbleBlockMixin extends Block {
    public BubbleBlockMixin(Properties p_49795_) {
        super(p_49795_);
    }

    @Inject(method = "stepOn", at = @At("HEAD"), cancellable = true)
    private void stopThePop(Level level, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (entity instanceof Player player && player.getItemBySlot(EquipmentSlot.FEET).is(BBItems.BUBBLE_BOOTS.get())) {
            super.stepOn(level, pos, state, entity);
            ci.cancel();
        }
    }
    @Inject(method = "fallOn", at = @At("HEAD"), cancellable = true)
    private void stopThePopTheSqueakquel(Level level, BlockState state, BlockPos pos, Entity entity, float v, CallbackInfo ci) {
        if (!level.isClientSide && entity instanceof Player player && player.getItemBySlot(EquipmentSlot.FEET).is(BBItems.BUBBLE_BOOTS.get()) && v < 2.0F) {
            super.fallOn(level, state, pos, entity, v);
            ci.cancel();
        }
    }
}
