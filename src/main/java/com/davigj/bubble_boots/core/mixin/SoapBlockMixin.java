package com.davigj.bubble_boots.core.mixin;

import com.davigj.bubble_boots.common.item.BubbleBootsItem;
import com.davigj.bubble_boots.core.BBConfig;
import com.davigj.bubble_boots.core.registry.BBItems;
import net.mehvahdjukaar.supplementaries.common.block.blocks.SoapBlock;
import net.mehvahdjukaar.supplementaries.reg.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.davigj.bubble_boots.common.item.BubbleBootsItem.SOAPINESS;
import static com.davigj.bubble_boots.common.util.Constants.MAX_SOAPINESS;

@Mixin(SoapBlock.class)
public class SoapBlockMixin extends Block {
    public SoapBlockMixin(Properties p_49795_) {
        super(p_49795_);
    }

    @Inject(method = "stepOn", at = @At("HEAD"))
    private void reSoap(Level level, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (BBConfig.COMMON.soapBlockRestoration.get() && entity instanceof LivingEntity living) {
            ItemStack stack = living.getItemBySlot(EquipmentSlot.FEET);
            if (stack.is(BBItems.BUBBLE_BOOTS.get())) {
                CompoundTag tag = stack.getOrCreateTag();
                int soapiness = tag.getInt(SOAPINESS);
                if (soapiness != MAX_SOAPINESS) {
                    level.playSound(entity, pos, ModSounds.BUBBLE_BLOW.get(), SoundSource.NEUTRAL, 0.8F, 1.0F);
                    ((BubbleBootsItem)(stack.getItem())).setDefaultSoapiness(stack);
                }
            }
        }
    }
}
