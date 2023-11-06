package com.davigj.bubble_boots.core.other;

import com.davigj.bubble_boots.common.item.BubbleBootsItem;
import com.davigj.bubble_boots.core.BBConfig;
import com.davigj.bubble_boots.core.BBMod;
import com.davigj.bubble_boots.core.registry.BBItems;
import com.davigj.bubble_boots.core.registry.BBSounds;
import com.teamabnormals.blueprint.core.util.TradeUtil;
import net.mehvahdjukaar.supplementaries.common.items.SoapItem;
import net.mehvahdjukaar.supplementaries.reg.ModParticles;
import net.mehvahdjukaar.supplementaries.reg.ModRegistry;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.davigj.bubble_boots.common.item.BubbleBootsItem.SOAPINESS;
import static com.davigj.bubble_boots.common.util.Constants.MAX_SOAPINESS;

@Mod.EventBusSubscriber(modid = BBMod.MOD_ID)
public class BBEvents {

    @SubscribeEvent
    public static void playerUse(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof SoapItem) {
            ItemStack armorStack = player.getItemBySlot(EquipmentSlot.FEET);
            if (armorStack.getItem() instanceof BubbleBootsItem) {
                int soapiness = armorStack.getOrCreateTag().getInt(SOAPINESS);
                if (soapiness < MAX_SOAPINESS) {
                    armorStack.getOrCreateTag().putInt("Soapiness", Math.min(MAX_SOAPINESS, soapiness + BBConfig.COMMON.soapRestoreAmt.get()));
                    ItemStack handStack = player.getItemInHand(event.getHand());
                    player.swing(event.getHand());
                    if (!player.getAbilities().instabuild) {
                        handStack.shrink(1);
                    }
                    player.level.playSound((Player) player, player.blockPosition(), BBSounds.BUBBLES.get(),
                            SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (player.level.isClientSide && BBConfig.CLIENT.sudsyBoots.get()) {
                        RandomSource rand = player.getRandom();
                        for (int i = 0; i < 8; i++) {
                            double x = player.getX() - 0.5;
                            double y = player.getY();
                            double z = player.getZ() - 0.5;
                            double d3 = (float) x + rand.nextFloat();
                            double d6 = (float) z + rand.nextFloat();
                            player.level.addParticle(ModParticles.SUDS_PARTICLE.get(), d3, y + 0.025, d6, 0, 0, 0);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void villagerTrades(VillagerTradesEvent event) {
        if (BBConfig.COMMON.armorerTrade.get()) {
            TradeUtil.addVillagerTrades(event, VillagerProfession.ARMORER, TradeUtil.EXPERT, new TradeUtil.BlueprintTrade(
                    new ItemStack(ModRegistry.SOAP_BLOCK.get(), 2), new ItemStack(Items.EMERALD, 23),
                    new ItemStack(BBItems.BUBBLE_BOOTS.get(), 1), 3, 20, 5
            ));
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player) {
            return;
        } else if (entity.tickCount % 20 == 0 && entity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof BubbleBootsItem
                && entity.level.isClientSide && BBConfig.CLIENT.sudsyBoots.get()) {
            ItemStack stack = entity.getItemBySlot(EquipmentSlot.FEET);
            int soapiness = stack.getOrCreateTag().getInt(SOAPINESS);
            if (soapiness > 0) {
                RandomSource rand = entity.getRandom();
                double x = entity.getX() - 0.5;
                double y = entity.getY();
                double z = entity.getZ() - 0.5;
                double d3 = (float) x + rand.nextFloat();
                double d6 = (float) z + rand.nextFloat();
                entity.level.addParticle(ModParticles.SUDS_PARTICLE.get(), d3, y + 0.025, d6, 0, 0, 0);
            }
        }
    }

}