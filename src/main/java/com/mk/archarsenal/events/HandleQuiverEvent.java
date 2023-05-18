package com.mk.archarsenal.events;

import com.mk.archarsenal.ArchArsenal;
import com.mk.archarsenal.items.ModBowItem;
import com.mk.archarsenal.items.QuiverItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArchArsenal.MODID)
public class HandleQuiverEvent {

    @SubscribeEvent
    public static void handleQuiver(LivingEntityUseItemEvent.Start event) {

        LivingEntity livingEntity = event.getEntity();
        Level world = livingEntity.getLevel();

        if (livingEntity instanceof Player player && !(world.isClientSide)) {

            ItemStack offhandItem = player.getOffhandItem();
            ItemStack bow = player.getItemInHand(player.getUsedItemHand());

            if (offhandItem.getItem() instanceof QuiverItem quiver && bow.getItem() instanceof BowItem) {

                boolean NAT_INFINITY = false;

                if (bow.getItem() instanceof ModBowItem modBow) {
                    NAT_INFINITY = modBow.getNatInfinity();
                }

                boolean isInfinite = player.getAbilities().instabuild
                        || EnchantmentHelper.getTagEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow) > 0
                        || NAT_INFINITY;

                if (!isInfinite) {
                    ItemStack arrows = player.getProjectile(bow);
                    boolean roll = quiver.rollArrowSave(world);
                    System.out.println("rolling...");
                    if(roll) {
                        arrows.grow(1);
                        offhandItem.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(player.getUsedItemHand()));
                    }
                }
            }
        }
    }
}
