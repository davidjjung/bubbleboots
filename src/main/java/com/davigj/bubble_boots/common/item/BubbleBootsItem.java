package com.davigj.bubble_boots.common.item;

import net.mehvahdjukaar.supplementaries.reg.ModParticles;
import net.mehvahdjukaar.supplementaries.reg.ModRegistry;
import net.mehvahdjukaar.supplementaries.reg.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static com.davigj.bubble_boots.common.util.Constants.MAX_SOAPINESS;

public class BubbleBootsItem extends ArmorItem {
    public static final String SOAPINESS = "Soapiness";

    public BubbleBootsItem(ArmorMaterial materialIn, EquipmentSlot slot, Item.Properties builder) {
        super(materialIn, slot, builder);
    }

    public void onArmorTick(ItemStack stack, Level world, Player player) {
        CompoundTag tag = stack.getOrCreateTag();
        int soapiness = tag.getInt(SOAPINESS);
        BlockPos bubblePos = player.blockPosition().below();
        if (world.getBlockState(bubblePos).isAir() && soapiness > 0) {
            if (!world.isClientSide) {
                world.setBlockAndUpdate(bubblePos, ModRegistry.BUBBLE_BLOCK.get().defaultBlockState());
                if (player.tickCount % 2 == 0) {
                    tag.putInt(SOAPINESS, soapiness - 1);
                }
            }
        } else if (world.getBlockState(bubblePos).is(ModRegistry.SOAP_BLOCK.get()) && soapiness != MAX_SOAPINESS) {
            world.playSound(player, player, ModSounds.BUBBLE_PLACE.get(), SoundSource.NEUTRAL, 1.0F, 3.0F);
            this.setDefaultSoapiness(stack);
        }
        if ((player.tickCount % 12 == 0 && soapiness > 0) || (soapiness > 0 && soapiness < 15 && player.tickCount % 4 == 0)) {
            Random rand = new Random();
            double x = player.getX() - 0.5;
            double y = player.getY();
            double z = player.getZ() - 0.5;
            double d3 = (float) x + rand.nextFloat();
            double d6 = (float) z + rand.nextFloat();
            world.addParticle(ModParticles.SUDS_PARTICLE.get(), d3, y + 0.025, d6, 0, 0, 0);
        }
    }

    public int getBarWidth(ItemStack stack) {
        int soapiness = stack.getOrCreateTag().getInt(SOAPINESS);
        return Math.round(13.0F - (float) (MAX_SOAPINESS - soapiness) * 13.0F / MAX_SOAPINESS);
    }

    public boolean isBarVisible(ItemStack stack) {
        int soapiness = stack.getOrCreateTag().getInt(SOAPINESS);
        return soapiness > 0;
    }

    public int getBarColor(@NotNull ItemStack stack) {
        return 15246564;
    }

    private void setDefaultSoapiness(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt(SOAPINESS, MAX_SOAPINESS);
    }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        int soapiness = stack.getOrCreateTag().getInt(SOAPINESS);
        if (soapiness != 0) {
            tooltip.add(Component.translatable("message.supplementaries.bubble_blower_tooltip", new Object[]{soapiness, MAX_SOAPINESS}));
        }
    }

    public boolean isEnchantable(@NotNull ItemStack p_41456_) {
        return false;
    }

    @Override
    public boolean isDamaged(ItemStack stack) {
        return false;
    }
    @Override
    public int getMaxDamage(ItemStack stack) {
        return 0;
    }
}