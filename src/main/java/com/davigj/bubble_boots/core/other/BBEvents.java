package com.davigj.bubble_boots.core.other;

import com.davigj.bubble_boots.common.item.BubbleBootsItem;
import com.davigj.bubble_boots.core.BBConfig;
import com.davigj.bubble_boots.core.BBMod;
import net.mehvahdjukaar.supplementaries.common.items.SoapItem;
import net.mehvahdjukaar.supplementaries.reg.ModRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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
                    handStack.shrink(1);
                }
            }
        }
    }

}