package com.davigj.bubble_boots.common.item;

import com.davigj.bubble_boots.core.BBConfig;
import com.davigj.bubble_boots.core.registry.BBSounds;
import net.mehvahdjukaar.supplementaries.common.block.blocks.SoapBlock;
import net.mehvahdjukaar.supplementaries.reg.ModParticles;
import net.mehvahdjukaar.supplementaries.reg.ModRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static com.davigj.bubble_boots.common.util.Constants.MAX_SOAPINESS;

public class BubbleBootsItem extends ArmorItem {
    public BubbleBootsItem(Holder<ArmorMaterial> materialIn, Item.Properties builder) {
        super(materialIn, Type.BOOTS, builder);
    }

    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof BubbleBootsItem) {
            stack.getDamageValue();
            int soapiness = stack.getDamageValue();
            BlockPos bubblePos = entity.blockPosition().below();
            if (soapiness > 0) {
                int tickCount = entity.tickCount;
                int soapWarning = BBConfig.CLIENT.soapWarning.get();
                if (level.getBlockState(bubblePos).isAir() && !entity.isCrouching()) {
                    if (!level.isClientSide) {
                        level.setBlock(bubblePos, ModRegistry.BUBBLE_BLOCK.get().defaultBlockState(), 3);
                        if (tickCount % 2 == 0) {
                            stack.setDamageValue(soapiness - 1);
                        }
                    } else {
                        if (tickCount % 2 == 0 && soapiness == soapWarning) {
                            level.playSound(entity, entity.blockPosition(), BBSounds.BUBBLES.get(),
                                    SoundSource.PLAYERS, 0.5F, (float) (0.9F + (0.25 * entity.getRandom().nextGaussian())));
                        }
                    }
                } else if (entity.isInWaterRainOrBubble() && BBConfig.COMMON.bootCleaning.get()) {
                    stack.setDamageValue(MAX_SOAPINESS);
                    return;
                }
                if (level.isClientSide && BBConfig.CLIENT.sudsyBoots.get() && (tickCount % 40 == 0 || soapiness < soapWarning && tickCount % 4 == 0)) {
                    RandomSource rand = entity.getRandom();
                    double x = entity.getX() - 0.5;
                    double y = entity.getY();
                    double z = entity.getZ() - 0.5;
                    double d3 = (float) x + rand.nextFloat();
                    double d6 = (float) z + rand.nextFloat();
                    level.addParticle(ModParticles.SUDS_PARTICLE.get(), d3, y + 0.025, d6, 0, 0, 0);
                }
                if (BBConfig.COMMON.slipAndSlide.get()) {
                    ((SoapBlock) ModRegistry.SOAP_BLOCK.get()).stepOn(level, entity.getOnPos(), ModRegistry.SOAP_BLOCK.get().defaultBlockState(), entity);
                }
            }
        }
    }

    public int getBarWidth(ItemStack stack) {
        return Math.round(13.0F - (float) (MAX_SOAPINESS - stack.getDamageValue()) * 13.0F / MAX_SOAPINESS);
    }

    public boolean isBarVisible(ItemStack stack) {
        return stack.getDamageValue() > 0;
    }

    public int getBarColor(@NotNull ItemStack stack) {
        return 15246564;
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        int soapiness = stack.getDamageValue();
        if (soapiness != 0) {
            tooltip.add(Component.translatable("message.supplementaries.bubble_blower_tooltip", new Object[]{soapiness, MAX_SOAPINESS}));
        }
        tooltip.add(Component.translatable("item.bubble_boots.bubble_boots_midair").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.bubble_boots.bubble_boots_effect").withStyle(ChatFormatting.BLUE));
    }

    public boolean isEnchantable(@NotNull ItemStack p_41456_) {
        return false;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return MAX_SOAPINESS;
    }
}